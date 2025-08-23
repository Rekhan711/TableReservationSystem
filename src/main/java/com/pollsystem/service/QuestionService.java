package com.pollsystem.service;

import com.pollsystem.entity.Question;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с вопросами (Question).
 *
 * Интерфейс описывает доступные операции:
 *  - saveQuestion(question) → сохранить или обновить вопрос;
 *  - findById(id) → найти вопрос по ID;
 *  - findAll() → получить список всех вопросов;
 *  - deleteById(id) → удалить вопрос по ID.
 *
 * Контроллеры используют этот сервис, а не напрямую репозиторий.
 */
public interface QuestionService {
    Question saveQuestion(Question question);
    Optional<Question> findById(Long id);
    List<Question> findAll();
    void deleteById(Long id);
}
