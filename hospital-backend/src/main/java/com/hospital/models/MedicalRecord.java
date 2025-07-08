package com.hospital.models;

import java.sql.Timestamp;
import com.hospital.models.Appointment;
import com.hospital.models.Patient;
import com.hospital.models.Doctor;
import com.hospital.models.User;
/**
 * The MedicalRecord class represents a medical record associated with an appointment.
 * It contains details about the patient, doctor, diagnosis, treatment, and other relevant information.
 */


public class MedicalRecord {
    private int id;
    private int appointmentId;
    private Appointment appointment;
    private int patientId;
    private Patient patient;
    private int doctorId;
    private Doctor doctor;
    private String diagnosis;
    private String treatment;
    private String prescription;
    private String notes;
    private Timestamp createdAt;

    public MedicalRecord() {}

    public MedicalRecord(int id, int appointmentId, Appointment appointment, int patientId, Patient patient, int doctorId, Doctor doctor,
                         String diagnosis, String treatment, String prescription, String notes, Timestamp createdAt) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.appointment = appointment;
        this.patientId = patientId;
        this.patient = patient;
        this.doctorId = doctorId;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.prescription = prescription;
        this.notes = notes;
        this.createdAt = createdAt;
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
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String getTreatment() {
        return treatment;
    }
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    public String getPrescription() {
        return prescription;
    }
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", appointmentId=" + appointmentId +
                ", appointment=" + (appointment != null ? appointment.toString() : "N/A") +
                ", patientId=" + patientId +
                ", patient=" + (patient != null ? patient.toString() : "N/A") +
                ", doctorId=" + doctorId +
                ", doctor=" + (doctor != null ? doctor.toString() : "N/A") +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                ", prescription='" + prescription + '\'' +
                ", notes='" + notes + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
    
}
