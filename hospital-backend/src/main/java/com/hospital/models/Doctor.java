package com.hospital.models;
import java.sql.Timestamp;

public class Doctor extends User {
    private int departmentId;
    private String specialization;
    private String licenseNumber;
    private boolean superUser;

    public Doctor() {}

    public Doctor(int id, String name, String email, String password, String phone, String role, Timestamp createdAt,
                  int departmentId, String specialization, String licenseNumber, boolean superUser) {
        super(id, name, email, password, phone, role, createdAt);
        this.departmentId = departmentId;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.superUser = superUser;
    }

    // Getters and setters...
    public int getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", departmentId=" + departmentId +
                ", specialization='" + specialization + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", superUser=" + superUser +
                ", role='" + getRole() + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
