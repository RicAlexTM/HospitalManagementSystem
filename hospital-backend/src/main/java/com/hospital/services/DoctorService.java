package com.hospital.services;

import java.util.List;

import com.hospital.models.Doctor;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> findAll();
    Optional<Doctor> findById(int id);
    boolean create(Doctor doctor);
    boolean update(Doctor doctor);
    boolean delete(int id);
    List<Doctor> findByDepartment(int departmentId);
    List<Doctor> findBySpecialization(String specialization);
}