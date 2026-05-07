package com.bookmyshow.dto;

import com.bookmyshow.entity.showStatusEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class showCreateRequestDto {

    @NotNull
    private Long movieId;

    @NotNull
    private Long screenId;

    @NotNull
    private LocalDateTime showStartTime;

    @NotNull
    private LocalDateTime showEndTime;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal basePrice;

    private showStatusEnum status;

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
