package com.hospital.models;
import com.hospital.models.User;
import java.sql.Timestamp;
import java.sql.Date;
/**
 * Represents an Admin user in the hospital management system.
 * Extends the User class to include additional properties specific to Admins.
 */

public class Admin extends User {
    private boolean isSuperAdmin;
    private Integer departmentId; // nullable
    private String accessLevel;

    public Admin() {}

    public Admin(int id, String name, String email, String password, String phone, String role, Timestamp createdAt,
                 boolean isSuperAdmin, Integer departmentId, String accessLevel) {
        super(id, name, email, password, phone, role, createdAt);
        this.isSuperAdmin = isSuperAdmin;
        this.departmentId = departmentId;
        this.accessLevel = accessLevel;
    }

    // Getters and setters...
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
    public String getAccessLevel() {
        return accessLevel;
    }
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", isSuperAdmin=" + isSuperAdmin +
                ", departmentId=" + departmentId +
                ", accessLevel='" + accessLevel + '\'' +
                ", role='" + getRole() + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
