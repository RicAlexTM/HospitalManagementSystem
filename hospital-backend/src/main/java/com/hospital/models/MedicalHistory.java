package com.hospital.models;

import java.sql.Date;

/**
 * The MedicalHistory class represents a patient's past medical conditions and treatments.
 * This class demonstrates a "has-a" and associative relationship with the Patient class.
 */
public class MedicalHistory {

    private int id;

    // "has-a" relationship with Patient — composition
    private int patientId;
    private Patient patient;

    private String conditionName;
    private Date diagnosisDate;
    private String treatment;
    private String notes;

    // Default constructor
    public MedicalHistory() {}

    /**
     * Constructor shows a "uses-a" relationship with Patient.
     * This setup allows MedicalHistory to be initialized with a Patient instance.
     */
    public MedicalHistory(int id, int patientId, Patient patient, String conditionName,
                          Date diagnosisDate, String treatment, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.patient = patient;
        this.conditionName = conditionName;
        this.diagnosisDate = diagnosisDate;
        this.treatment = treatment;
        this.notes = notes;
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

    /**
     * toString shows polymorphism if Patient overrides toString()
     */
    @Override
    public String toString() {
        return "MedicalHistory{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", patient=" + (patient != null ? patient.getName() : "N/A") +
                ", conditionName='" + conditionName + '\'' +
                ", diagnosisDate=" + diagnosisDate +
                ", treatment='" + treatment + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
