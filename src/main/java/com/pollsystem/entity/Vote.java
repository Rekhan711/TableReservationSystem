package com.pollsystem.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Голос пользователя за вариант ответа или текстовый ответ.
 * <p>
 * - Если вопрос типа SINGLE/MULTIPLE_CHOICE → хранится выбранный {@link Option}.
 * - Если вопрос типа TEXT → хранится значение в {@link #textAnswer}.
 * </p>
 */
@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

    /** Уникальный идентификатор голоса */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Пользователь, оставивший голос */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** Выбранный вариант ответа (для choice-вопросов) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option;

    /** Текстовый ответ (для вопросов типа TEXT) */
    @Column(length = 2000)
    private String textAnswer;
}
