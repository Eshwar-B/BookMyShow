package com.bookmyshow.dto;

import com.bookmyshow.entity.showStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class showResponseDto {
    private Long id;
    private Long movieId;
    private Long screenId;
    private LocalDateTime showStartTime;
    private LocalDateTime showEndTime;
    private BigDecimal basePrice;
    private showStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getScreenId() {
        return screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(LocalDateTime showStartTime) {
        this.showStartTime = showStartTime;
    }

    public LocalDateTime getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(LocalDateTime showEndTime) {
        this.showEndTime = showEndTime;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public showStatusEnum getStatus() {
        return status;
    }

    public void setStatus(showStatusEnum status) {
        this.status = status;
    }
}
