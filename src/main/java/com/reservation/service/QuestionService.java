package com.reservation.service;

import com.reservation.entity.Question;
import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Question saveQuestion(Question question);
    Optional<Question> findById(Long id);
    List<Question> findAll();
    void deleteById(Long id);
}
