package com.pollsystem.repository;

import com.pollsystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с вопросами (Question).
 *
 * JpaRepository даёт базовые CRUD-методы.
 *
 * Дополнительно можно добавить кастомные запросы:
 *  - List<Question> findByPollId(Long pollId) → получить вопросы для конкретного опроса.
 *
 * Используется в QuestionService для работы с вопросами.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
