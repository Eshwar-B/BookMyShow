package com.bookmyshow.repository;

import com.bookmyshow.entity.showEntity;
import com.bookmyshow.entity.showStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface showRepository extends JpaRepository<showEntity, Long> {
    List<showEntity> findByMovieIdAndShowStartTimeAfterAndStatus(Long movieId, LocalDateTime showStartTime, showStatusEnum status);
}
