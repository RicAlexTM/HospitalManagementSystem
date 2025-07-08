package com.hospital.services;


import com.hospital.models.MedicalHistory;
import java.util.Optional;

import java.util.List;

public interface MedicalHistoryService {
    List<MedicalHistory> findAll();
    Optional<MedicalHistory> findById(int id);
    boolean create(MedicalHistory history);
    boolean update(MedicalHistory history);
    boolean delete(int id);
    List<MedicalHistory> findByPatientId(int patientId);
    List<MedicalHistory> findByCondition(String condition);
}
