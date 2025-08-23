package com.pollsystem.service;

import com.pollsystem.entity.Poll;
import com.pollsystem.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса PollService.
 *
 * Аннотация @Service → обозначает этот класс как Spring-сервис.
 * Аннотация @RequiredArgsConstructor → создаёт конструктор для final-полей.
 *
 * Сервис служит "прослойкой" между контроллерами и репозиторием.
 * Все операции делегируются в PollRepository, но при необходимости
 * сюда можно добавить дополнительную бизнес-логику (валидацию,
 * проверки доступа, обработку ошибок).
 */
@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository; // внедряется Spring'ом

    @Override
    public Poll savePoll(Poll poll) {
        return pollRepository.save(poll); // сохранить/обновить опрос
    }

    @Override
    public Optional<Poll> findById(Long id) {
        return pollRepository.findById(id); // найти по ID
    }

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll(); // получить все опросы
    }

    @Override
    public void deleteById(Long id) {
        pollRepository.deleteById(id); // удалить по ID
    }
}
