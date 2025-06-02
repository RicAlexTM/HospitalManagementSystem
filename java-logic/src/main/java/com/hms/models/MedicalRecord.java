package com.hms.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a Medical Record in the hospital management system.
 */
public class MedicalRecord {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String notes;
    private LocalDateTime createdAt;

    public MedicalRecord() {}

    public MedicalRecord(int id, Patient patient, Doctor doctor, String notes, LocalDateTime createdAt) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.notes = notes;
        this.createdAt = createdAt;
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

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", patient=" + (patient != null ? patient.getName() : null) +
                ", doctor=" + (doctor != null ? doctor.getName() : null) +
                ", notes='" + notes + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalRecord)) return false;
        MedicalRecord that = (MedicalRecord) o;
        return id == that.id &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctor, notes, createdAt);
    }
}