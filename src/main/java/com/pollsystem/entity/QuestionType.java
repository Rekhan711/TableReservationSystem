package com.pollsystem.entity;

/**
 * Типы вопросов в опросе:
 * - SINGLE_CHOICE → один вариант ответа
 * - MULTIPLE_CHOICE → несколько вариантов ответа
 * - TEXT → свободный текстовый ответ
 */
public enum QuestionType {
    SINGLE_CHOICE,
    MULTIPLE_CHOICE,
    TEXT
}
