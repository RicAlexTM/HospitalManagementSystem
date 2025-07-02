package com.hospital.models;

public class Admin {
    private int userId;
    private User user;
    private boolean isSuperAdmin;
    private Integer departmentId; // nullable
    private Department department;
    private String accessLevel;

    public Admin() {}

    public Admin(int userId, User user, boolean isSuperAdmin, Integer departmentId, Department department, String accessLevel) {
        this.userId = userId;
        this.user = user;
        this.isSuperAdmin = isSuperAdmin;
        this.departmentId = departmentId;
        this.department = department;
        this.accessLevel = accessLevel;
    }

    // Getters and Setters

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

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
