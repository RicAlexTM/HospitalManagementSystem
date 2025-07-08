package com.hospital.daos;

import com.hospital.models.Patient;
import java.util.List;

public interface PatientDAO extends CrudDAO<Patient, Integer> {
    List<Patient> findByBloodType(String bloodType);
}