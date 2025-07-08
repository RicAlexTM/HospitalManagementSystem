package com.hospital.controllers;

import java.util.List;

import com.hospital.daos.MedicalRecordDAO;
import com.hospital.daos.impl.MedicalRecordDAOImpl;
import com.hospital.models.MedicalRecord;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class MedicalRecordController {
    private final MedicalRecordDAO medicalRecordDAO;

    public MedicalRecordController() {
        this.medicalRecordDAO = new MedicalRecordDAOImpl();
    }

    public void setupRoutes(Javalin app) {
        // Get all medical records
        app.get("/medical-records", this::getAllMedicalRecords);
        
        // Get medical record by ID
        app.get("/medical-records/{id}", this::getMedicalRecordById);
        
        // Create new medical record
        app.post("/medical-records", this::createMedicalRecord);
        
        // Update medical record
        app.put("/medical-records/{id}", this::updateMedicalRecord);
        
        // Delete medical record
        app.delete("/medical-records/{id}", this::deleteMedicalRecord);
        
        // Get records by patient
        app.get("/patients/{patientId}/medical-records", this::getRecordsByPatient);
        
        // Get records by doctor
        app.get("/doctors/{doctorId}/medical-records", this::getRecordsByDoctor);
    }

    private void getAllMedicalRecords(Context ctx) {
        List<MedicalRecord> records = medicalRecordDAO.findAll();
        ctx.json(records).status(200);
    }

    private void getMedicalRecordById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        medicalRecordDAO.findById(id).ifPresentOrElse(
            record -> ctx.json(record).status(200),
            () -> ctx.status(404).result("Medical record not found")
        );
    }

    private void createMedicalRecord(Context ctx) {
        MedicalRecord record = ctx.bodyAsClass(MedicalRecord.class);
        if (medicalRecordDAO.create(record)) {
            ctx.status(201).json(record);
        } else {
            ctx.status(400).result("Failed to create medical record");
        }
    }

    private void updateMedicalRecord(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        MedicalRecord updatedRecord = ctx.bodyAsClass(MedicalRecord.class);
        updatedRecord.setId(id);
        
        if (medicalRecordDAO.update(updatedRecord)) {
            ctx.status(200).json(updatedRecord);
        } else {
            ctx.status(400).result("Failed to update medical record");
        }
    }

    private void deleteMedicalRecord(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (medicalRecordDAO.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Medical record not found");
        }
    }

    private void getRecordsByPatient(Context ctx) {
        int patientId = Integer.parseInt(ctx.pathParam("patientId"));
        List<MedicalRecord> records = medicalRecordDAO.findByPatientId(patientId);
        ctx.json(records).status(200);
    }

    private void getRecordsByDoctor(Context ctx) {
        int doctorId = Integer.parseInt(ctx.pathParam("doctorId"));
        List<MedicalRecord> records = medicalRecordDAO.findByDoctorId(doctorId);
        ctx.json(records).status(200);
    }
}