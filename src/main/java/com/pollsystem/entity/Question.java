package com.pollsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Вопрос внутри опроса.
 * <p>
 * Может быть одного из трёх типов:
 * - {@link QuestionType#SINGLE_CHOICE} – выбор одного варианта
 * - {@link QuestionType#MULTIPLE_CHOICE} – выбор нескольких вариантов
 * - {@link QuestionType#TEXT} – текстовый ответ
 * </p>
 */
@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    /** Уникальный идентификатор вопроса */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Текст вопроса */
    @Column(nullable = false, length = 500)
    private String text;

    /** Тип вопроса */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    /** Связанный опрос */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    /** Список вариантов ответа (для choice-вопросов) */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;
}
