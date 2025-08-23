package com.pollsystem.service;

import com.pollsystem.entity.User;
import com.pollsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса для работы с пользователями.
 * Отвечает за регистрацию, поиск и сохранение пользователей в БД.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Сохраняет пользователя в БД (без дополнительных проверок).
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Ищет пользователя по username.
     * @param username имя пользователя
     * @return Optional<User> — если найден
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Возвращает список всех пользователей.
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Регистрация нового пользователя:
     * 1. Проверяет уникальность username.
     * 2. Шифрует пароль.
     * 3. Назначает дефолтную роль USER, если ничего не указано.
     */
    @Override
    public User registerUser(User user) {
        // Проверка на уникальность username
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        // Шифрование пароля через BCrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Назначаем дефолтную роль (если роли не заданы)
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Collections.singleton("USER"));
        }

        return userRepository.save(user);
    }
}
