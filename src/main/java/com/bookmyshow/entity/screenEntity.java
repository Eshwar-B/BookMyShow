package com.bookmyshow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "screens")
public class screenEntity extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theatre_id", nullable = false)
    private theatreEntity theatre;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(name = "total_rows", nullable = false)
    private Integer totalRows;

    @Column(name = "total_seats_per_row", nullable = false)
    private Integer totalSeatsPerRow;

    @Column(nullable = false)
    private Boolean active = true;

    public Long getId() {
        return id;
    }

    public theatreEntity getTheatre() {
        return theatre;
    }

    public void setTheatre(theatreEntity theatre) {
        this.theatre = theatre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalSeatsPerRow() {
        return totalSeatsPerRow;
    }

    public void setTotalSeatsPerRow(Integer totalSeatsPerRow) {
        this.totalSeatsPerRow = totalSeatsPerRow;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
