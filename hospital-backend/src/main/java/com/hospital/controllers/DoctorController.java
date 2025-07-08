package com.hospital.controllers;

import com.hospital.daos.DoctorDAO;
import com.hospital.daos.impl.DoctorDAOImpl;
import com.hospital.models.Doctor;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

public class DoctorController {
    private final DoctorDAO doctorDAO;

    public DoctorController() {
        this.doctorDAO = new DoctorDAOImpl();
    }

    public void setupRoutes(Javalin app) {
        // Get all doctors
        app.get("/doctors", this::getAllDoctors);
        
        // Get doctor by ID
        app.get("/doctors/{id}", this::getDoctorById);
        
        // Create new doctor
        app.post("/doctors", this::createDoctor);
        
        // Update doctor
        app.put("/doctors/{id}", this::updateDoctor);
        
        // Delete doctor
        app.delete("/doctors/{id}", this::deleteDoctor);
        
        // Get doctors by department
        app.get("/doctors/department/{deptId}", this::getDoctorsByDepartment);
        
        // Get doctors by specialization
        app.get("/doctors/specialization/{spec}", this::getDoctorsBySpecialization);
    }

    private void getAllDoctors(Context ctx) {
        List<Doctor> doctors = doctorDAO.findAll();
        ctx.json(doctors).status(200);
    }

    private void getDoctorById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        doctorDAO.findById(id).ifPresentOrElse(
            doctor -> ctx.json(doctor).status(200),
            () -> ctx.status(404).result("Doctor not found")
        );
    }

    private void createDoctor(Context ctx) {
        Doctor doctor = ctx.bodyAsClass(Doctor.class);
        if (doctorDAO.create(doctor)) {
            ctx.status(201).json(doctor);
        } else {
            ctx.status(400).result("Failed to create doctor");
        }
    }

    private void updateDoctor(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Doctor updatedDoctor = ctx.bodyAsClass(Doctor.class);
        updatedDoctor.setId(id);
        
        if (doctorDAO.update(updatedDoctor)) {
            ctx.status(200).json(updatedDoctor);
        } else {
            ctx.status(400).result("Failed to update doctor");
        }
    }

    private void deleteDoctor(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (doctorDAO.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Doctor not found");
        }
    }

    private void getDoctorsByDepartment(Context ctx) {
        int deptId = Integer.parseInt(ctx.pathParam("deptId"));
        List<Doctor> doctors = doctorDAO.findByDepartment(deptId);
        ctx.json(doctors).status(200);
    }

    private void getDoctorsBySpecialization(Context ctx) {
        String specialization = ctx.pathParam("spec");
        List<Doctor> doctors = doctorDAO.findBySpecialization(specialization);
        ctx.json(doctors).status(200);
    }
}