package com.bookmyshow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
public class showEntity extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private movieEntity movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "screen_id", nullable = false)
    private screenEntity screen;

    @Column(name = "show_start_time", nullable = false)
    private LocalDateTime showStartTime;

    @Column(name = "show_end_time", nullable = false)
    private LocalDateTime showEndTime;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private showStatusEnum status = showStatusEnum.SCHEDULED;

    public Long getId() {
        return id;
    }

    public movieEntity getMovie() {
        return movie;
    }

    public void setMovie(movieEntity movie) {
        this.movie = movie;
    }

    public screenEntity getScreen() {
        return screen;
    }

    public void setScreen(screenEntity screen) {
        this.screen = screen;
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
