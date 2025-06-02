package com.hms.models;

import java.util.Objects;

/**
 * Represents a Patient in the hospital management system.
 */
public class Patient extends User {
    private String bloodType;
    private String medicalHistory;

    /**
     * Default constructor sets the role to "Patient".
     */
    public Patient() {
        this.role = "Patient";
    }

    /**
     * Parameterized constructor for easier instantiation.
     */
    public Patient(String name, String email, String password, String bloodType, String medicalHistory) {
        this.role = "Patient";
        this.name = name;
        this.email = email;
        this.password = password;
        this.bloodType = bloodType;
        this.medicalHistory = medicalHistory;
    }

    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(bloodType, patient.bloodType) &&
                Objects.equals(medicalHistory, patient.medicalHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bloodType, medicalHistory);
    }
}