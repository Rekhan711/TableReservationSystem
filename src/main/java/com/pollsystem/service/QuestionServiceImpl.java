package com.pollsystem.service;

import com.pollsystem.entity.Question;
import com.pollsystem.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Реализация QuestionService.
 *
 * Здесь выполняются CRUD-операции с вопросами.
 * Пока сервис напрямую делегирует методы в QuestionRepository,
 * но его можно расширить:
 *  - добавлять логику (например, проверку, что вопрос принадлежит опросу);
 *  - валидацию данных;
 *  - обработку ошибок.
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question); // сохранить/обновить
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id); // найти по ID
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll(); // список всех вопросов
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id); // удалить по ID
    }
}
