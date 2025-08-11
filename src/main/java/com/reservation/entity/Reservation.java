package com.reservation.entity;

import jakarta.persistence.*;

import java.util.UUID;

// Reservation.java
@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @Column(name="party_size", nullable = false)
    private int partySize;

    @Column(name="start_time", nullable = false)
    private OffsetDateTime startTime;

    @Column(name="end_time", nullable = false)
    private OffsetDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(name="created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name="updated_at")
    private OffsetDateTime updatedAt;
    // getters/setters
}
