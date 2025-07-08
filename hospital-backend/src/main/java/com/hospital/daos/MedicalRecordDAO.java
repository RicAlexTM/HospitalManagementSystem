package com.hospital.daos;

import com.hospital.models.MedicalRecord;
import java.util.List;

public interface MedicalRecordDAO extends CrudDAO<MedicalRecord, Integer> {
    List<MedicalRecord> findByPatientId(int patientId);
    List<MedicalRecord> findByDoctorId(int doctorId);
}