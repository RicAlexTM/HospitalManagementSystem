package com.hospital.controllers;

import java.util.List;

import com.hospital.models.Doctor;
import com.hospital.models.User;
import com.hospital.services.DoctorService;

import io.javalin.http.Context;

public class DoctorController {

    private final DoctorService doctorService = new DoctorService();

    public void getAllDoctors(Context ctx) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        ctx.json(doctors);
    }

    public void getDoctorById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Doctor doctor = doctorService.getDoctorById(id);

        if (doctor != null) {
            ctx.json(doctor);
        } else {
            ctx.status(404).result("Doctor not found");
        }
    }

    public void registerDoctor(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            Doctor doctor = ctx.bodyAsClass(Doctor.class);

            boolean created = doctorService.registerNewDoctor(user, doctor);

            if (created) {
                ctx.status(201).result("Doctor registered successfully");
            } else {
                ctx.status(400).result("Failed to register doctor");
            }

        } catch (Exception e) {
            ctx.status(500).result("Error parsing doctor data");
        }
    }
}
