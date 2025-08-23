package com.pollsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Сущность пользователя.
 * Хранит информацию о логине, пароле, email и ролях.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Первичный ключ

    @Column(nullable = false, unique = true, length = 50)
    private String username; // Логин пользователя

    @Column(nullable = false)
    private String password; // Хранится в зашифрованном виде

    @Column(nullable = false, unique = true, length = 100)
    private String email; // Email пользователя

    /**
     * Роли пользователя (например, USER или ADMIN).
     * EAGER — подгружаются сразу с пользователем.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    /**
     * Список опросов, созданных пользователем.
     * mappedBy = "creator" → поле creator в сущности Poll.
     */
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Poll> polls;
}
