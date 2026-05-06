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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "seats",
        uniqueConstraints = @UniqueConstraint(name = "uk_seat_screen_row_num", columnNames = {"screen_id", "seat_row", "seat_number"})
)
public class seatEntity extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "screen_id", nullable = false)
    private screenEntity screen;

    @Column(name = "seat_row", nullable = false, length = 10)
    private String seatRow;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false, length = 20)
    private seatTypeEnum seatType = seatTypeEnum.REGULAR;

    @Column(nullable = false)
    private Boolean active = true;

    public Long getId() {
        return id;
    }

    public screenEntity getScreen() {
        return screen;
    }

    public void setScreen(screenEntity screen) {
        this.screen = screen;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public seatTypeEnum getSeatType() {
        return seatType;
    }

    public void setSeatType(seatTypeEnum seatType) {
        this.seatType = seatType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
