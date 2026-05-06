package com.bookmyshow.repository;

import com.bookmyshow.entity.screenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface screenRepository extends JpaRepository<screenEntity, Long> {
    List<screenEntity> findByTheatreIdAndActiveTrue(Long theatreId);
}
