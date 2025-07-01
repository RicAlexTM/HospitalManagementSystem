package com.hospital.controllers;

import com.hospital.models.MedicalHistory;
import com.hospital.services.MedicalHistoryService;
import io.javalin.http.Context;

import java.util.List;

public class MedicalHistoryController {

    private final MedicalHistoryService historyService = new MedicalHistoryService();

    public void getAllHistories(Context ctx) {
        List<MedicalHistory> histories = historyService.getAllHistories();
        ctx.json(histories);
    }

    public void getHistoriesByPatient(Context ctx) {
        int patientId = Integer.parseInt(ctx.pathParam("patientId"));
        List<MedicalHistory> histories = historyService.getHistoriesByPatientId(patientId);
        ctx.json(histories);
    }

    public void addHistory(Context ctx) {
        try {
            MedicalHistory history = ctx.bodyAsClass(MedicalHistory.class);
            boolean added = historyService.addHistory(history);

            if (added) {
                ctx.status(201).result("Medical history added");
            } else {
                ctx.status(400).result("Failed to add medical history");
            }

        } catch (Exception e) {
            ctx.status(500).result("Invalid request");
        }
    }
}
