package com.hms.models;

public abstract class User {
    protected int userid;
    protected String name;
    protected String email;
    protected String password;
    // Role can be "Admin", "Doctor", or "Patient"
    protected String role;

    // Getters
    public int getId() { return userid; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getPassword() { return password; }

    // Setters
    public void setId(int id) { this.userid = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setPassword(String password) { this.password = password; }
}