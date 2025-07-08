package com.hospital.models;
import com.hospital.models.User;
import java.sql.Timestamp;


import java.sql.Date;

public class Patient extends User {
    private Date dateOfBirth;
    private String bloodType;

    public Patient() {}

    public Patient(int id, String name, String email, String password, String phone, String role, Timestamp createdAt,
                   Date dateOfBirth, String bloodType) {
        super(id, name, email, password, phone, role, createdAt);
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
    }

    // Getters and setters...
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bloodType='" + bloodType + '\'' +
                ", role='" + getRole() + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
