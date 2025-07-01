package com.hospital.models;

public class Doctor {
    private int userId;
    private User user;
    private int departmentId;
    private Department department;
    private String specialization;
    private String licenseNumber;
    private boolean superUser;

    public Doctor() {}

    public Doctor(int userId, User user, int departmentId, Department department, String specialization, String licenseNumber, boolean superUser) {
        this.userId = userId;
        this.user = user;
        this.departmentId = departmentId;
        this.department = department;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.superUser = superUser;
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
    public int getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public boolean isSuperUser() {
        return superUser;
    }
    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }
    

}
