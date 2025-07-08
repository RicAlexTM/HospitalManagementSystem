package com.hospital.controllers;

import java.util.List;

import com.hospital.daos.AppointmentDAO;
import com.hospital.daos.impl.AppointmentDAOImpl;
import com.hospital.models.Appointment;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class AppointmentController {
    private final AppointmentDAO appointmentDAO;

    public AppointmentController() {
        this.appointmentDAO = new AppointmentDAOImpl();
    }

    public void setupRoutes(Javalin app) {
        // Get all appointments
        app.get("/appointments", this::getAllAppointments);
        
        // Get appointment by ID
        app.get("/appointments/{id}", this::getAppointmentById);
        
        // Create new appointment
        app.post("/appointments", this::createAppointment);
        
        // Update appointment
        app.put("/appointments/{id}", this::updateAppointment);
        
        // Delete appointment
        app.delete("/appointments/{id}", this::deleteAppointment);
        
        // Get appointments by patient
        app.get("/patients/{patientId}/appointments", this::getAppointmentsByPatient);
        
        // Get appointments by doctor
        app.get("/doctors/{doctorId}/appointments", this::getAppointmentsByDoctor);
        
        // Update appointment status
        app.patch("/appointments/{id}/status", this::updateAppointmentStatus);
    }

    private void getAllAppointments(Context ctx) {
        List<Appointment> appointments = appointmentDAO.findAll();
        ctx.json(appointments).status(200);
    }

    private void getAppointmentById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        appointmentDAO.findById(id).ifPresentOrElse(
            appointment -> ctx.json(appointment).status(200),
            () -> ctx.status(404).result("Appointment not found")
        );
    }

    private void createAppointment(Context ctx) {
        Appointment appointment = ctx.bodyAsClass(Appointment.class);
        if (appointmentDAO.create(appointment)) {
            ctx.status(201).json(appointment);
        } else {
            ctx.status(400).result("Failed to create appointment");
        }
    }

    private void updateAppointment(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Appointment updatedAppointment = ctx.bodyAsClass(Appointment.class);
        updatedAppointment.setId(id);
        
        if (appointmentDAO.update(updatedAppointment)) {
            ctx.status(200).json(updatedAppointment);
        } else {
            ctx.status(400).result("Failed to update appointment");
        }
    }

    private void deleteAppointment(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (appointmentDAO.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Appointment not found");
        }
    }

    private void getAppointmentsByPatient(Context ctx) {
        int patientId = Integer.parseInt(ctx.pathParam("patientId"));
        List<Appointment> appointments = appointmentDAO.findByPatientId(patientId);
        ctx.json(appointments).status(200);
    }

    private void getAppointmentsByDoctor(Context ctx) {
        int doctorId = Integer.parseInt(ctx.pathParam("doctorId"));
        List<Appointment> appointments = appointmentDAO.findByDoctorId(doctorId);
        ctx.json(appointments).status(200);
    }

    private void updateAppointmentStatus(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String newStatus = ctx.body();
        
        if (appointmentDAO.updateStatus(id, newStatus)) {
            ctx.status(200).result("Appointment status updated");
        } else {
            ctx.status(400).result("Failed to update status");
        }
    }
}