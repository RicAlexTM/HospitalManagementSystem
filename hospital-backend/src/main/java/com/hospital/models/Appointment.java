package com.hospital.models;

import java.sql.Timestamp;

public class Appointment {
    private int id;
    private int patientId;
    private Patient patient;
    private int doctorId;
    private Doctor doctor;
    private Timestamp appointmentDate;
    private String status;
    private String reason;
    private Timestamp createdAt;

    public Appointment() {}

    public Appointment(int id, int patientId, Patient patient, int doctorId, Doctor doctor, Timestamp appointmentDate, String status, String reason, Timestamp createdAt) {
        this.id = id;
        this.patientId = patientId;
        this.patient = patient;
        this.doctorId = doctorId;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    // Getters and Setters...
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
}
