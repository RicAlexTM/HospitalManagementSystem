package com.hospital.controllers;

import com.hospital.daos.MedicalHistoryDAO;
import com.hospital.daos.impl.MedicalHistoryDAOImpl;
import com.hospital.models.MedicalHistory;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

public class MedicalHistoryController {
    private final MedicalHistoryDAO medicalHistoryDAO;

    public MedicalHistoryController() {
        this.medicalHistoryDAO = new MedicalHistoryDAOImpl();
    }

    public void setupRoutes(Javalin app) {
        // Get all medical histories
        app.get("/medical-histories", this::getAllMedicalHistories);
        
        // Get medical history by ID
        app.get("/medical-histories/{id}", this::getMedicalHistoryById);
        
        // Create new medical history
        app.post("/medical-histories", this::createMedicalHistory);
        
        // Update medical history
        app.put("/medical-histories/{id}", this::updateMedicalHistory);
        
        // Delete medical history
        app.delete("/medical-histories/{id}", this::deleteMedicalHistory);
        
        // Get histories by patient
        app.get("/patients/{patientId}/medical-histories", this::getHistoriesByPatient);
        
        // Get histories by condition
        app.get("/medical-histories/condition/{condition}", this::getHistoriesByCondition);
    }

    private void getAllMedicalHistories(Context ctx) {
        List<MedicalHistory> histories = medicalHistoryDAO.findAll();
        ctx.json(histories).status(200);
    }

    private void getMedicalHistoryById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        medicalHistoryDAO.findById(id).ifPresentOrElse(
            history -> ctx.json(history).status(200),
            () -> ctx.status(404).result("Medical history not found")
        );
    }

    private void createMedicalHistory(Context ctx) {
        MedicalHistory history = ctx.bodyAsClass(MedicalHistory.class);
        if (medicalHistoryDAO.create(history)) {
            ctx.status(201).json(history);
        } else {
            ctx.status(400).result("Failed to create medical history");
        }
    }

    private void updateMedicalHistory(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        MedicalHistory updatedHistory = ctx.bodyAsClass(MedicalHistory.class);
        updatedHistory.setId(id);
        
        if (medicalHistoryDAO.update(updatedHistory)) {
            ctx.status(200).json(updatedHistory);
        } else {
            ctx.status(400).result("Failed to update medical history");
        }
    }

    private void deleteMedicalHistory(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (medicalHistoryDAO.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Medical history not found");
        }
    }

    private void getHistoriesByPatient(Context ctx) {
        int patientId = Integer.parseInt(ctx.pathParam("patientId"));
        List<MedicalHistory> histories = medicalHistoryDAO.findByPatientId(patientId);
        ctx.json(histories).status(200);
    }

    private void getHistoriesByCondition(Context ctx) {
        String condition = ctx.pathParam("condition");
        List<MedicalHistory> histories = medicalHistoryDAO.findByCondition(condition);
        ctx.json(histories).status(200);
    }
}