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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class paymentEntity extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private bookingEntity booking;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 30)
    private paymentMethodEnum paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 20)
    private paymentStatusEnum paymentStatus = paymentStatusEnum.PENDING;

    @Column(name = "transaction_reference", length = 80)
    private String transactionReference;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    public Long getId() {
        return id;
    }

    public bookingEntity getBooking() {
        return booking;
    }

    public void setBooking(bookingEntity booking) {
        this.booking = booking;
    }

    public paymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(paymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public paymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(paymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }
}
