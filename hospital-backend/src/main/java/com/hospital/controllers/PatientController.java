package com.hospital.controllers;

import java.util.List;

import com.hospital.models.Patient;
import com.hospital.models.User;
import com.hospital.services.PatientService;

import io.javalin.http.Context;

public class PatientController {

    private final PatientService patientService = new PatientService();

    public void getAllPatients(Context ctx) {
        List<Patient> patients = patientService.getAllPatients();
        ctx.json(patients);
    }

    public void getPatientById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Patient patient = patientService.getPatientById(id);

        if (patient != null) {
            ctx.json(patient);
        } else {
            ctx.status(404).result("Patient not found");
        }
    }

    public void registerPatient(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            Patient patient = ctx.bodyAsClass(Patient.class);

            boolean created = patientService.registerNewPatient(user, patient);

            if (created) {
                ctx.status(201).result("Patient registered successfully");
            } else {
                ctx.status(400).result("Failed to register patient");
            }

        } catch (Exception e) {
            ctx.status(500).result("Error parsing patient data");
        }
    }
}
