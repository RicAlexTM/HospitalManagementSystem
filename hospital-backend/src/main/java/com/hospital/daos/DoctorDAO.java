package com.hospital.daos;

import com.hospital.models.Doctor;
import java.util.List;

public interface DoctorDAO extends CrudDAO<Doctor, Integer> {
    List<Doctor> findByDepartment(int departmentId);
    List<Doctor> findBySpecialization(String specialization);
}