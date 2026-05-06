package com.bookmyshow.repository;

import com.bookmyshow.entity.seatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface seatRepository extends JpaRepository<seatEntity, Long> {
    List<seatEntity> findByScreenIdAndActiveTrue(Long screenId);
}
