package com.pollsystem.service;

import com.pollsystem.entity.Poll;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с опросами (Poll).
 *
 * Интерфейс определяет основные операции над сущностью Poll:
 *  - savePoll(poll) → сохранить или обновить опрос;
 *  - findById(id) → найти опрос по его ID;
 *  - findAll() → получить список всех опросов;
 *  - deleteById(id) → удалить опрос по ID.
 *
 * Используется контроллерами (например, PollController),
 * чтобы изолировать их от прямой работы с репозиториями.
 */
public interface PollService {
    Poll savePoll(Poll poll);
    Optional<Poll> findById(Long id);
    List<Poll> findAll();
    void deleteById(Long id);
}
