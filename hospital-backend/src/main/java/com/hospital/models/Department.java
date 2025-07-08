package com.hospital.models;

import java.sql.Timestamp;
import java.util.List;

/**
 * The Department class represents a unit in the hospital.
 * It "has-a" relationship with Doctor — i.e., a department can have multiple doctors.
 */
public class Department {

    private int id;
    private String name;
    private String description;
    private Timestamp createdAt;

    // "has-a" relationship: one department has many doctors
    private List<Doctor> doctors;

    // Default constructor
    public Department() {}

    /**
     * Constructor demonstrates a "uses-a" and "has-a" relationship
     * by accepting a list of doctors and other fields.
     */
    public Department(int id, String name, String description, Timestamp createdAt, List<Doctor> doctors) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.doctors = doctors;
    }

    // Basic fields
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Relationship-based methods
    public List<Doctor> getDoctors() {
        return doctors;
    }
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    /**
     * Shows polymorphic behavior if Doctor overrides toString().
     */
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", doctors=" + (doctors != null ? doctors.size() : 0) +
                '}';
    }
}
