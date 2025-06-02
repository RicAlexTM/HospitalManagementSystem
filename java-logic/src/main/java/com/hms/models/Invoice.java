package com.hms.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an Invoice in the hospital management system.
 */
public class Invoice {
    private int id;
    private Patient patient;
    private double amount;
    private LocalDateTime dateIssued;
    private boolean paid;
    private String description;

    public Invoice() {}

    public Invoice(int id, Patient patient, double amount, LocalDateTime dateIssued, boolean paid, String description) {
        this.id = id;
        this.patient = patient;
        this.amount = amount;
        this.dateIssued = dateIssued;
        this.paid = paid;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateIssued() {
        return dateIssued;
    }
    public void setDateIssued(LocalDateTime dateIssued) {
        this.dateIssued = dateIssued;
    }

    public boolean isPaid() {
        return paid;
    }
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", patient=" + (patient != null ? patient.getName() : null) +
                ", amount=" + amount +
                ", dateIssued=" + dateIssued +
                ", paid=" + paid +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id &&
                Double.compare(invoice.amount, amount) == 0 &&
                paid == invoice.paid &&
                Objects.equals(patient, invoice.patient) &&
                Objects.equals(dateIssued, invoice.dateIssued) &&
                Objects.equals(description, invoice.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, amount, dateIssued, paid, description);
    }
}