package com.hospital.services;

import java.util.List;
import com.hospital.models.Patient;
import java.util.Optional;



public interface PatientService {
    List<Patient> findAll();
    Optional<Patient> findById(int id);
    boolean create(Patient patient);
    boolean update(Patient patient);
    boolean delete(int id);
    List<Patient> findByBloodType(String bloodType);
}


//implemented single responsibility principle
// this service interface is responsible for managing patient-related operations