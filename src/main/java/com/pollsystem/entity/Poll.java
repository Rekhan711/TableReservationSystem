package com.pollsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Опрос, созданный пользователем.
 * <p>
 * Содержит список вопросов ({@link Question}) и принадлежит конкретному автору ({@link User}).
 * </p>
 */
@Entity
@Table(name = "polls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poll {

    /** Уникальный идентификатор опроса */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Заголовок опроса */
    @Column(nullable = false, length = 200)
    private String title;

    /** Описание (необязательное) */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** Создатель опроса */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    /** Список вопросов, связанных с опросом */
    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    /** Дата создания опроса */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /** Дата окончания (null → бессрочный) */
    private LocalDateTime expiresAt;
}
