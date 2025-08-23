package com.pollsystem.repository;

import com.pollsystem.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с опросами (Poll).
 *
 * Через JpaRepository доступны CRUD-операции:
 *  - findAll(), findById(id), save(poll), deleteById(id).
 *
 * Можно расширять методами поиска, например:
 *  - List<Poll> findByCreator(User user)
 *  - List<Poll> findByExpiresAtBefore(LocalDateTime time)
 *
 * Используется в PollService для управления опросами.
 */
public interface PollRepository extends JpaRepository<Poll, Long> {
}
