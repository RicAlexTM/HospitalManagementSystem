package com.hospital.services;

import com.hospital.daos.MedicalHistoryDAO;
import com.hospital.models.MedicalHistory;

import java.util.List;

public class MedicalHistoryService {

    public List<MedicalHistory> getAllHistories() {
        return MedicalHistoryDAO.getAllMedicalHistories();
    }

    public List<MedicalHistory> getHistoriesByPatientId(int patientId) {
        return MedicalHistoryDAO.getByPatientId(patientId);
    }

    public boolean addHistory(MedicalHistory history) {
        return MedicalHistoryDAO.addMedicalHistory(history);
    }
}
