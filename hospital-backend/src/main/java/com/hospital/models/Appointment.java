package com.hospital.models;

import java.sql.Timestamp;
import com.hospital.models.Patient;
import com.hospital.models.Doctor;
import com.hospital.models.User;


/**
 * The Appointment class represents a scheduled meeting between
 * a Patient and a Doctor — it "has-a" relationship with both.
 */
public class Appointment {

    private int id;

    // "has-a" relationship (composition)
    private int patientId;
    private Patient patient; // Appointment has a Patient

    private int doctorId;
    private Doctor doctor;   // Appointment has a Doctor

    private Timestamp appointmentDate;
    private String status;
    private String reason;
    private Timestamp createdAt;

    // Default constructor
    public Appointment() {}

    /**
     * Constructor demonstrates a "uses-a" relationship: Appointment uses
     * Patient and Doctor objects to create an instance.
     */
    public Appointment(int id, int patientId, Patient patient,
                       int doctorId, Doctor doctor,
                       Timestamp appointmentDate, String status,
                       String reason, Timestamp createdAt) {
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

    // Getters and Setters (encapsulation)
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

    /**
     * toString demonstrates polymorphic behavior if Patient or Doctor override toString().
     */
    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient=" + (patient != null ? patient.getName() : "N/A") +
                ", doctor=" + (doctor != null ? doctor.getName() : "N/A") +
                ", appointmentDate=" + appointmentDate +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
