package com.bookmyshow.repository;

import com.bookmyshow.entity.seatInventoryStatusEnum;
import com.bookmyshow.entity.showSeatInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface showSeatInventoryRepository extends JpaRepository<showSeatInventoryEntity, Long> {
    Optional<showSeatInventoryEntity> findByShowIdAndSeatId(Long showId, Long seatId);

    List<showSeatInventoryEntity> findByShowIdAndStatus(Long showId, seatInventoryStatusEnum status);
}
