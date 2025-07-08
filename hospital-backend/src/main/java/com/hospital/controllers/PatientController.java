package com.hospital.controllers;

import java.util.List;

import com.hospital.daos.PatientDAO;
import com.hospital.daos.impl.PatientDAOImpl;
import com.hospital.models.Patient;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class PatientController {
    private final PatientDAO patientDAO;

    public PatientController() {
        this.patientDAO = new PatientDAOImpl();
    }

    public void setupRoutes(Javalin app) {
        // Get all patients
        app.get("/patients", this::getAllPatients);
        
        // Get patient by ID
        app.get("/patients/{id}", this::getPatientById);
        
        // Create new patient
        app.post("/patients", this::createPatient);
        
        // Update patient
        app.put("/patients/{id}", this::updatePatient);
        
        // Delete patient
        app.delete("/patients/{id}", this::deletePatient);
        
        // Get patients by blood type
        app.get("/patients/blood-type/{type}", this::getPatientsByBloodType);
    }

    private void getAllPatients(Context ctx) {
        List<Patient> patients = patientDAO.findAll();
        ctx.json(patients).status(200);
    }

    private void getPatientById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        patientDAO.findById(id).ifPresentOrElse(
            patient -> ctx.json(patient).status(200),
            () -> ctx.status(404).result("Patient not found")
        );
    }

    private void createPatient(Context ctx) {
        Patient patient = ctx.bodyAsClass(Patient.class);
        if (patientDAO.create(patient)) {
            ctx.status(201).json(patient);
        } else {
            ctx.status(400).result("Failed to create patient");
        }
    }

    private void updatePatient(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Patient updatedPatient = ctx.bodyAsClass(Patient.class);
        updatedPatient.setId(id);
        
        if (patientDAO.update(updatedPatient)) {
            ctx.status(200).json(updatedPatient);
        } else {
            ctx.status(400).result("Failed to update patient");
        }
    }

    private void deletePatient(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (patientDAO.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Patient not found");
        }
    }

    private void getPatientsByBloodType(Context ctx) {
        String bloodType = ctx.pathParam("type");
        List<Patient> patients = patientDAO.findByBloodType(bloodType);
        ctx.json(patients).status(200);
    }
}