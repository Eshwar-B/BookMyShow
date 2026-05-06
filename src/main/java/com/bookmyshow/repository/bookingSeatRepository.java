package com.bookmyshow.repository;

import com.bookmyshow.entity.bookingSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface bookingSeatRepository extends JpaRepository<bookingSeatEntity, Long> {
    List<bookingSeatEntity> findByBookingId(Long bookingId);
}
