package com.bookmyshow.repository;

import com.bookmyshow.entity.paymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface paymentRepository extends JpaRepository<paymentEntity, Long> {
    Optional<paymentEntity> findByBookingId(Long bookingId);
}
