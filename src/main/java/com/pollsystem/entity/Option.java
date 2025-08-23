package com.pollsystem.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Вариант ответа на вопрос.
 * <p>
 * Используется только в вопросах с типами SINGLE_CHOICE / MULTIPLE_CHOICE.
 * </p>
 */
@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Option {

    /** Уникальный идентификатор варианта */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Текст варианта ответа */
    @Column(nullable = false, length = 200)
    private String text;

    /** Связанный вопрос */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
