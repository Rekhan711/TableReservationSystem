package com.pollsystem.repository;

import com.pollsystem.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с голосами (Vote).
 *
 * Наследует стандартные CRUD-операции.
 *
 * Можно добавлять методы для анализа:
 *  - List<Vote> findByUserId(Long userId) → все голоса пользователя.
 *  - List<Vote> findByOptionId(Long optionId) → все голоса за конкретный вариант.
 *  - long countByOptionId(Long optionId) → подсчёт голосов за вариант.
 *
 * Используется в VoteService.
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
