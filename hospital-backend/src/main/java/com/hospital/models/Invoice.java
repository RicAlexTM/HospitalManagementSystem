package com.hospital.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Invoice {
    private int id;
    private int appointmentId;
    private Appointment appointment;
    private BigDecimal amount;
    private Timestamp issuedDate;
    private String paymentStatus;
    private String paymentMethod;

    public Invoice() {}

    public Invoice(int id, int appointmentId, Appointment appointment, BigDecimal amount, Timestamp issuedDate, String paymentStatus, String paymentMethod) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.appointment = appointment;
        this.amount = amount;
        this.issuedDate = issuedDate;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters...
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
    

}
