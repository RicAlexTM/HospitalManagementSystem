package com.hms.models;

/**
 * Represents an Admin user with superuser privileges and department assignment.
 */
public class Admin extends User {
    private String department;
    private boolean superUser; // true if admin has all privileges

    /**
     * Default constructor sets the role to "Admin".
     */
    public Admin() {
        this.role = "Admin";
    }

    /**
     * Parameterized constructor for easier instantiation.
     */
    public Admin(String name, String email, String password, String department, boolean superUser) {
        this.role = "Admin";
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.superUser = superUser;
    }

    // Getter and Setter for department
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    // Getter and Setter for superUser
    public boolean isSuperUser() {
        return superUser;
    }
    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", superUser=" + superUser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return userid == admin.userid &&
                superUser == admin.superUser &&
                name.equals(admin.name) &&
                email.equals(admin.email) &&
                password.equals(admin.password) &&
                department.equals(admin.department);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(userid);
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + Boolean.hashCode(superUser);
        return result;
    }
}