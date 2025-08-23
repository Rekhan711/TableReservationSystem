package com.pollsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Конфигурация безопасности.
 * Определяет bean PasswordEncoder для шифрования паролей.
 */
@Configuration
public class SecurityConfig {

    /**
     * BCryptPasswordEncoder — алгоритм для безопасного хэширования паролей.
     * Используется в UserServiceImpl при регистрации пользователей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
