package com.pollsystem.repository;

import com.pollsystem.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с вариантами ответа (Option).
 *
 * Наследует JpaRepository, что автоматически даёт набор CRUD-операций:
 *  - findAll() → получить все варианты
 *  - findById(id) → найти вариант по ID
 *  - save(option) → сохранить/обновить вариант
 *  - deleteById(id) → удалить по ID
 *
 * Обычно используется в сервисе QuestionService для работы с вариантами ответа.
 */
public interface OptionRepository extends JpaRepository<Option, Long> {
}
