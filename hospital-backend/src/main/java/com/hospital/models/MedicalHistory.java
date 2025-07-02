package com.hospital.models;

import java.sql.Date;

public class MedicalHistory {
    private int id;
    private int patientId;
    private Patient patient; 
    private String conditionName;
    private Date diagnosisDate;
    private String treatment;
    private String notes;

    public MedicalHistory() {}

    public MedicalHistory(int id, int patientId, Patient patient, String conditionName, Date diagnosisDate, String treatment, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.patient = patient;
        this.conditionName = conditionName;
        this.diagnosisDate = diagnosisDate;
        this.treatment = treatment;
        this.notes = notes;
    }

    // Getters and Setters

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

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
