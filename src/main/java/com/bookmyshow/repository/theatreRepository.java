package com.bookmyshow.repository;

import com.bookmyshow.entity.theatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface theatreRepository extends JpaRepository<theatreEntity, Long> {
    List<theatreEntity> findByCityAndActiveTrue(String city);
}
