package com.hospital.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The Invoice class represents billing details for an appointment.
 * It demonstrates a "has-a" relationship with the Appointment class.
 */
public class Invoice {

    private int id;

    // "has-a" relationship (composition)
    private int appointmentId;
    private Appointment appointment;

    private BigDecimal amount;
    private Timestamp issuedDate;
    private String paymentStatus;
    private String paymentMethod;

    // Default constructor
    public Invoice() {}

    /**
     * "uses-a" relationship is shown through constructor injection.
     * Invoice depends on Appointment to be meaningful.
     */
    public Invoice(int id, int appointmentId, Appointment appointment, BigDecimal amount,
                   Timestamp issuedDate, String paymentStatus, String paymentMethod) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.appointment = appointment;
        this.amount = amount;
        this.issuedDate = issuedDate;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
    }

    // Encapsulation through getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Appointment getAppointment() {
        return appointment;
    }
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getIssuedDate() {
        return issuedDate;
    }
    public void setIssuedDate(Timestamp issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Demonstrates polymorphism if Appointment overrides toString()
     */
    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", appointmentId=" + appointmentId +
                ", appointment=" + (appointment != null ? appointment.toString() : "N/A") +
                ", amount=" + amount +
                ", issuedDate=" + issuedDate +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
