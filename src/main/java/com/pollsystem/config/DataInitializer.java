package com.pollsystem.config;

import com.pollsystem.entity.User;
import com.pollsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * Конфигурационный класс для инициализации данных в БД при запуске приложения.
 * Здесь создаётся дефолтный пользователь-админ, если в базе ещё нет записи с username="admin".
 */
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    // PasswordEncoder внедряется через DI, нужен для шифрования паролей пользователей
    private final PasswordEncoder passwordEncoder;

    /**
     * CommandLineRunner — это специальный бин Spring Boot,
     * который запускается автоматически после поднятия контекста приложения.
     *
     * В данном случае он используется для:
     *  1. Проверки наличия в базе данных пользователя с логином "admin".
     *  2. Если такого пользователя нет — создаётся новый.
     *  3. Это гарантирует, что при первом запуске в системе всегда будет
     *     хотя бы один администратор, чтобы можно было войти в приложение.
     */
    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            // Проверяем: существует ли уже пользователь с username = "admin"
            if (userRepository.findByUsername("admin").isEmpty()) {

                // Создаём нового пользователя-админа
                User admin = new User();
                admin.setUsername("admin"); // Логин для входа
                admin.setPassword(passwordEncoder.encode("admin123"));
                // Пароль: "admin123" — сохраняем только в зашифрованном виде (через PasswordEncoder/BCrypt).
                // Никогда нельзя хранить "чистый" пароль в базе данных.

                admin.setRoles(Collections.singleton("ADMIN"));
                // Задаём роли пользователю. Здесь создаётся Set, содержащий единственную роль — "ADMIN".

                // Сохраняем пользователя в базу данных через Spring Data JPA
                userRepository.save(admin);

                // Выводим сообщение в консоль для удобства (чтобы разработчик видел, что админ создан)
                System.out.println("✅ Default admin created: username=admin, password=admin123");
            }
            // Если пользователь "admin" уже существует — ничего не делаем.
        };
    }

    /**
     * CommandLineRunner-бин, который выполняется автоматически при запуске приложения.
     * Его задача — инициализировать базу данных дефолтным пользователем (админом),
     * если он ещё не существует.
     *
     * ВАЖНО:
     * - Выполняется один раз при старте (после того как Spring поднял контекст).
     * - Позволяет гарантировать, что в системе всегда будет доступен администратор.
     */
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            // Проверяем: существует ли пользователь с username "admin"
            if (userRepository.findByUsername("admin").isEmpty()) {

                // Создаём нового пользователя
                User admin = new User();
                admin.setUsername("admin"); // логин администратора
                admin.setPassword(encoder.encode("admin123")); // пароль, хэшированный через PasswordEncoder (BCrypt)
                admin.setEmail("admin@example.com"); // обязательное поле email (иначе будет ошибка NOT NULL)

                // Сохраняем пользователя в БД через Spring Data JPA
                userRepository.save(admin);

                // (опционально можно добавить System.out.println — для наглядности)
                System.out.println("✅ Default admin created: username=admin, password=admin123");
            }
            // Если админ уже есть, то ничего не делаем
        };
    }
}
