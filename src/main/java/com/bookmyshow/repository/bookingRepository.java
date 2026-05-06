package com.bookmyshow.repository;

import com.bookmyshow.entity.bookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface bookingRepository extends JpaRepository<bookingEntity, Long> {
    Optional<bookingEntity> findByBookingReference(String bookingReference);

    List<bookingEntity> findByUserIdOrderByBookedAtDesc(Long userId);
}
