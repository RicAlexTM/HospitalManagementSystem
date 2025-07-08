package com.hospital.daos;

import com.hospital.models.MedicalHistory;
import java.util.List;

public interface MedicalHistoryDAO extends CrudDAO<MedicalHistory, Integer> {
    List<MedicalHistory> findByPatientId(int patientId);
    List<MedicalHistory> findByCondition(String conditionName);
}