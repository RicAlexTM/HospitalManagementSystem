package com.hospital.services;

import com.hospital.models.MedicalRecord;
import java.util.List;
import java.util.Optional;


public interface MedicalRecordService {
    List<MedicalRecord> findAll();
    Optional<MedicalRecord> findById(int id);
    boolean create(MedicalRecord record);
    boolean update(MedicalRecord record);
    boolean delete(int id);
    List<MedicalRecord> findByPatientId(int patientId);
    List<MedicalRecord> findByDoctorId(int doctorId);
}