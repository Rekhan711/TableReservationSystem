package com.reservation.repository;

import com.reservation.entity.TableEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TableRepository extends JpaRepository<TableEntity, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from TableEntity t where t.restaurant.id = :restId and t.isActive = true and t.seats >= :partySize order by t.seats asc")
    List<TableEntity> findCandidatesForUpdate(UUID restId, int partySize);
}

