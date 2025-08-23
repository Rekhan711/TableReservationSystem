package com.pollsystem.repository;

import com.pollsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Репозиторий для работы с пользователями (User).
 *
 * Наследует CRUD из JpaRepository, плюс содержит кастомные методы:
 *  - Optional<User> findByUsername(String username) → поиск пользователя по логину.
 *  - boolean existsByUsername(String username) → проверка уникальности имени.
 *
 * Используется в UserService и при аутентификации в Spring Security.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
