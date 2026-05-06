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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "show_seat_inventory",
        uniqueConstraints = @UniqueConstraint(name = "uk_show_seat", columnNames = {"seat_id", "show_id"})
)
public class showSeatInventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seat_id", nullable = false)
    private seatEntity seat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "show_id", nullable = false)
    private showEntity show;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private seatInventoryStatusEnum status = seatInventoryStatusEnum.AVAILABLE;

    @Column(name = "lock_expires_at")
    private LocalDateTime lockExpiresAt;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public seatEntity getSeat() {
        return seat;
    }

    public void setSeat(seatEntity seat) {
        this.seat = seat;
    }

    public showEntity getShow() {
        return show;
    }

    public void setShow(showEntity show) {
        this.show = show;
    }

    public seatInventoryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(seatInventoryStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getLockExpiresAt() {
        return lockExpiresAt;
    }

    public void setLockExpiresAt(LocalDateTime lockExpiresAt) {
        this.lockExpiresAt = lockExpiresAt;
    }

    public Long getVersion() {
        return version;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
