package com.reservation.repository;

import com.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    @Query("select r from Reservation r where r.table.id = :tableId and r.status in :states and not (r.endTime <= :start or r.startTime >= :end)")
    List<Reservation> findConflictsForTable(UUID tableId, OffsetDateTime start, OffsetDateTime end, Collection<ReservationStatus> states);
}
