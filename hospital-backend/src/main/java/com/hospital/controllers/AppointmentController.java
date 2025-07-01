package com.hospital.controllers;

import java.util.List;

import com.hospital.models.Appointment;
import com.hospital.services.AppointmentService;

import io.javalin.http.Context;

public class AppointmentController {

    private final AppointmentService appointmentService = new AppointmentService();

    public void getAllAppointments(Context ctx) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        ctx.json(appointments);
    }

    public void getAppointmentById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Appointment appointment = appointmentService.getAppointmentById(id);

        if (appointment != null) {
            ctx.json(appointment);
        } else {
            ctx.status(404).result("Appointment not found");
        }
    }

    public void createAppointment(Context ctx) {
        try {
            Appointment appointment = ctx.bodyAsClass(Appointment.class);
            boolean created = appointmentService.scheduleAppointment(appointment);

            if (created) {
                ctx.status(201).result("Appointment created successfully");
            } else {
                ctx.status(400).result("Failed to create appointment");
            }

        } catch (Exception e) {
            ctx.status(500).result("Invalid appointment data");
        }
    }

    public void updateAppointmentStatus(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            String newStatus = ctx.queryParam("status");

            boolean updated = appointmentService.updateStatus(id, newStatus);

            if (updated) {
                ctx.status(200).result("Appointment status updated");
            } else {
                ctx.status(400).result("Failed to update appointment status");
            }

        } catch (Exception e) {
            ctx.status(500).result("Invalid request");
        }
    }
}
