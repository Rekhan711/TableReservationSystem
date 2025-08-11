package com.reservation.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

// TableEntity.java
@Entity
@Table(name="tables")
public class TableEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int seats;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
    // getters/setters
}
