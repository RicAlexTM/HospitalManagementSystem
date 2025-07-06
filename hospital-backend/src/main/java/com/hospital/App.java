package com.hospital;

import com.hospital.controllers.AdminController;
import com.hospital.controllers.AppointmentController;
import com.hospital.controllers.DoctorController;
import com.hospital.controllers.InvoiceController;
import com.hospital.controllers.MedicalHistoryController;
import com.hospital.controllers.PatientController;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> cors.add(it -> it.anyHost()));
        }).start(7000);

        app.get("/", ctx -> ctx.result("Hospital API is live!"));
        app.get("/api/ping", ctx -> ctx.json("{\"status\":\"ok\"}"));

        // Instantiate controllers
        PatientController patientController = new PatientController();
        DoctorController doctorController = new DoctorController();
        AppointmentController appointmentController = new AppointmentController();
        InvoiceController invoiceController = new InvoiceController();
        MedicalHistoryController medicalHistoryController = new MedicalHistoryController();
        AdminController adminController = new AdminController();

        // Patient routes
        app.get("/api/get_patients", patientController::getAllPatients);
        app.get("/api/get_patients/{id}", patientController::getPatientById);
        app.post("/api/register_patients", patientController::registerPatient);

        // Doctor routes
        app.get("/api/get_doctors", doctorController::getAllDoctors);
        app.get("/api/get_doctors/{id}", doctorController::getDoctorById);
        app.post("/api/register_doctors", doctorController::registerDoctor);

        // Appointment routes
        app.get("/api/get_appointments", appointmentController::getAllAppointments);
        app.get("/api/get_appointments/{id}", appointmentController::getAppointmentById);
        app.post("/api/create_appointments", appointmentController::createAppointment);
        app.put("/api/update_appointments/{id}", appointmentController::updateAppointmentStatus);

        // Invoice routes
        app.get("/api/get_invoices", invoiceController::getAllInvoices);
        app.get("/api/get_invoices/{id}", invoiceController::getInvoiceById);
        app.post("/api/create_invoices", invoiceController::createInvoice);
        app.put("/api/update_invoices/{id}", invoiceController::updatePaymentStatus);

        // Medical history routes
        app.get("/api/get_histories", medicalHistoryController::getAllHistories);
        app.get("/api/get_histories/patient/{patientId}", medicalHistoryController::getHistoriesByPatient);
        app.post("/api/create_histories", medicalHistoryController::addHistory);

        // Admin routes
        app.get("/api/get_admins", adminController::getAllAdmins);
        app.get("/api/get_admins/{userId}", adminController::getAdminByUserId);
        app.post("/api/register_admins", adminController::registerAdmin);
    }
}
