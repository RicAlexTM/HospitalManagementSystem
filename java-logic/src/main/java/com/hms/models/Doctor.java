package com.hms.models;

import java.util.Objects;

/**
 * Represents a Doctor in the hospital management system.
 */
public class Doctor extends User {
    private String specialization;
    private double consultationFee;

    /**
     * Default constructor sets the role to "Doctor".
     */
    public Doctor() {
        this.role = "Doctor";
    }

    /**
     * Parameterized constructor for easier instantiation.
     */
    public Doctor(String name, String email, String password, String specialization, double consultationFee) {
        this.role = "Doctor";
        this.name = name;
        this.email = email;
        this.password = password;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    // Getter and Setter for specialization
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Getter and Setter for consultationFee
    public double getConsultationFee() {
        return consultationFee;
    }
    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", specialization='" + specialization + '\'' +
                ", consultationFee=" + consultationFee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return Double.compare(doctor.consultationFee, consultationFee) == 0 &&
                Objects.equals(specialization, doctor.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), specialization, consultationFee);
    }
}