package com.pollsystem.service;

import com.pollsystem.entity.User;
import com.pollsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Реализация стандартного интерфейса {@link UserDetailsService} для Spring Security.
 * Используется при аутентификации пользователей.
 *
 * Загружает пользователя из БД по имени пользователя.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Ищет пользователя по имени (username) и формирует объект {@link UserDetails},
     * который используется Spring Security.
     *
     * @param username имя пользователя
     * @return объект {@link UserDetails} с данными о пользователе
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .build();
    }
}
