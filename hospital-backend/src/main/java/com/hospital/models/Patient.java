package com.hospital.models;

import java.sql.Date;

public class Patient {
    private int userId;
    private User user;
    private Date dateOfBirth;
    private String bloodType;

    public Patient() {}

    public Patient(int userId, User user, Date dateOfBirth, String bloodType) {
        this.userId = userId;
        this.user = user;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
    }

    // Getters and Setters...
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
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
    
}
