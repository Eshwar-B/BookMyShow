package com.bookmyshow.repository;

import com.bookmyshow.entity.movieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface movieRepository extends JpaRepository<movieEntity, Long> {
    List<movieEntity> findByActiveTrue();
}
