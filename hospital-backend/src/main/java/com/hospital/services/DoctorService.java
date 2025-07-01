package com.hospital.services;

import java.util.List;

import com.hospital.daos.DoctorDAO;
import com.hospital.daos.UserDAO;
import com.hospital.models.Doctor;
import com.hospital.models.User;

public class DoctorService {

    public List<Doctor> getAllDoctors() {
        return DoctorDAO.getAllDoctors();
    }

    public Doctor getDoctorById(int id) {
        return DoctorDAO.getDoctorById(id);
    }

    public boolean registerNewDoctor(User user, Doctor doctorDetails) {
        boolean userCreated = UserDAO.createUser(user);

        if (!userCreated) {
            return false;
        }

        doctorDetails.setUserId(user.getId());
        return DoctorDAO.createDoctor(doctorDetails);
    }
}
