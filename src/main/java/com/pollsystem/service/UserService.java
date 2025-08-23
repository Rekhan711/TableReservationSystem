package com.pollsystem.service;

import com.pollsystem.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс сервиса для работы с пользователями.
 */
public interface UserService {

    /**
     * Сохраняет пользователя в БД (обновляет, если он уже существует).
     *
     * @param user объект пользователя
     * @return сохранённый пользователь
     */
    User saveUser(User user);

    /**
     * Ищет пользователя по имени.
     *
     * @param username имя пользователя
     * @return Optional с пользователем
     */
    Optional<User> findByUsername(String username);

    /**
     * Возвращает список всех пользователей.
     *
     * @return список пользователей
     */
    List<User> findAll();

    /**
     * Регистрация нового пользователя (может включать шифрование пароля и валидацию).
     *
     * @param user объект пользователя
     * @return зарегистрированный пользователь
     */
    User registerUser(User user);
}
