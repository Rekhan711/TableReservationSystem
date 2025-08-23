package com.pollsystem.service;

import com.pollsystem.entity.Option;
import com.pollsystem.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса OptionService.
 *
 * Аннотация @Service → Spring помечает этот класс как компонент бизнес-логики.
 * Аннотация @RequiredArgsConstructor (Lombok) → автоматически создаёт
 * конструктор для final-полей (внедрение зависимостей).
 *
 * Здесь сервис делегирует вызовы в OptionRepository,
 * сохраняя принцип "разделения слоёв":
 *  - Контроллеры общаются только с сервисом;
 *  - Сервис работает с репозиторием (БД).
 */
@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository; // внедрение репозитория

    @Override
    public void saveOption(Option option) {
        optionRepository.save(option); // сохраняем или обновляем вариант
    }

    @Override
    public Optional<Option> findById(Long id) {
        return optionRepository.findById(id); // поиск по ID
    }

    @Override
    public List<Option> findAll() {
        return optionRepository.findAll(); // получить все варианты
    }

    @Override
    public void deleteById(Long id) {
        optionRepository.deleteById(id); // удаление по ID
    }
}
