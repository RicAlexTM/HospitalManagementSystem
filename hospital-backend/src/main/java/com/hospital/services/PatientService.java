package com.hospital.services;

import java.util.List;

import com.hospital.daos.PatientDAO;
import com.hospital.daos.UserDAO;
import com.hospital.models.Patient;
import com.hospital.models.User;

public class PatientService {

    public List<Patient> getAllPatients() {
        return PatientDAO.getAllPatients();
    }

    public Patient getPatientById(int id) {
        return PatientDAO.getPatientById(id);
    }

    public boolean registerNewPatient(User user, Patient patientDetails) {
        boolean userCreated = UserDAO.createUser(user);

        if (!userCreated) {
            return false;
        }

        // link patient to the generated user_id
        patientDetails.setUserId(user.getId());
        return PatientDAO.createPatient(patientDetails);
    }
}
