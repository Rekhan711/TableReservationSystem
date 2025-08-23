package com.pollsystem.service;

import com.pollsystem.entity.Option;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с вариантами ответа (Option).
 *
 * Этот интерфейс определяет контракт (API) для операций над вариантами:
 *  - saveOption(option) → сохранить или обновить вариант ответа;
 *  - findById(id) → найти вариант по ID;
 *  - findAll() → получить список всех вариантов;
 *  - deleteById(id) → удалить вариант по ID.
 *
 * Используется контроллерами (например, QuestionController),
 * чтобы скрыть детали доступа к БД (через OptionRepository).
 */
public interface OptionService {
    void saveOption(Option option);
    Optional<Option> findById(Long id);
    List<Option> findAll();
    void deleteById(Long id);
}
