package com.pollsystem.service;

import com.pollsystem.entity.Vote;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс сервиса для работы с голосами.
 */
public interface VoteService {

    /**
     * Сохраняет голос.
     *
     * @param vote объект голоса
     * @return сохранённый голос
     */
    Vote saveVote(Vote vote);

    /**
     * Находит голос по ID.
     *
     * @param id идентификатор голоса
     * @return Optional с голосом
     */
    Optional<Vote> findById(Long id);

    /**
     * Возвращает список всех голосов.
     *
     * @return список голосов
     */
    List<Vote> findAll();

    /**
     * Добавляет новый голос за вариант.
     *
     * @param optionId идентификатор варианта ответа
     * @return созданный и сохранённый голос
     */
    Vote castVote(Long optionId);
}
