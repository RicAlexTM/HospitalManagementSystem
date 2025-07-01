package com.hospital;

import io.javalin.Javalin;
import com.hospital.controllers.*;

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
        app.get("/api/patients", patientController::getAllPatients);
        app.get("/api/patients/:id", patientController::getPatientById);
        app.post("/api/patients", patientController::registerPatient);

        // Doctor routes
        app.get("/api/doctors", doctorController::getAllDoctors);
        app.get("/api/doctors/:id", doctorController::getDoctorById);
        app.post("/api/doctors", doctorController::registerDoctor);

        // Appointment routes
        app.get("/api/appointments", appointmentController::getAllAppointments);
        app.get("/api/appointments/:id", appointmentController::getAppointmentById);
        app.post("/api/appointments", appointmentController::createAppointment);
        app.put("/api/appointments/:id", appointmentController::updateAppointmentStatus);

        // Invoice routes
        app.get("/api/invoices", invoiceController::getAllInvoices);
        app.get("/api/invoices/:id", invoiceController::getInvoiceById);
        app.post("/api/invoices", invoiceController::createInvoice);
        app.put("/api/invoices/:id", invoiceController::updatePaymentStatus);

        // Medical history routes
        app.get("/api/histories", medicalHistoryController::getAllHistories);
        app.get("/api/histories/patient/:patientId", medicalHistoryController::getHistoriesByPatient);
        app.post("/api/histories", medicalHistoryController::addHistory);

        // Admin routes
        app.get("/api/admins", adminController::getAllAdmins);
        app.get("/api/admins/:userId", adminController::getAdminByUserId);
        app.post("/api/admins", adminController::registerAdmin);
    }
}
