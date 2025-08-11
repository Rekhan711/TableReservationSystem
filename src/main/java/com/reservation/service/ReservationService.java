package com.reservation.service;

import com.reservation.entity.Reservation;
import com.reservation.entity.TableEntity;
import com.reservation.repository.ReservationRepository;
import com.reservation.repository.TableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReservationResponseDTO createReservation(ReservationRequestDTO dto, UUID userId) {
        // 1. normalize times to restaurant timezone (store in UTC)
        Restaurant rest = restaurantRepository.findById(dto.getRestaurantId())
            .orElseThrow(() -> new ChangeSetPersister.NotFoundException("restaurant"));

        OffsetDateTime start = dto.getPreferredStartTime(); // assume already normalized to UTC
        OffsetDateTime end = start.plusMinutes(dto.getDurationMinutes());

        // validation (start >= now etc.) — omitted for brevity

        // 2. if preferredTableId provided — try reserve it with conflict check
        if (dto.getPreferredTableId() != null) {
            // lock table row
            TableEntity table = tableRepository.findById(dto.getPreferredTableId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("table"));
            List<Reservation> conflicts = reservationRepository.findConflictsForTable(table.getId(), start, end, List.of(PENDING, CONFIRMED));
            if (!conflicts.isEmpty()) throw new ConflictException("table occupied");
            // create reservation
            Reservation r = new Reservation(...);
            r.setTable(table);
            r.setStatus(ReservationStatus.CONFIRMED);
            reservationRepository.save(r);
            return toDto(r);
        }

        // 3. auto-assign: lock candidate tables (PESSIMISTIC_WRITE)
        List<TableEntity> candidates = tableRepository.findCandidatesForUpdate(rest.getId(), dto.getPartySize());
        for (TableEntity t : candidates) {
            List<Reservation> conflicts = reservationRepository.findConflictsForTable(t.getId(), start, end, List.of(PENDING, CONFIRMED));
            if (conflicts.isEmpty()) {
                Reservation r = new Reservation(...);
                r.setTable(t);
                r.setStatus(ReservationStatus.CONFIRMED);
                reservationRepository.save(r);
                return toDto(r);
            }
        }
        throw new ConflictException("no available table");
    }
}
