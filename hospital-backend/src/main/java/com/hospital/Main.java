package com.hospital;

import io.javalin.Javalin;

import com.hospital.controllers.AdminController;
import com.hospital.controllers.AppointmentController;
import com.hospital.controllers.DepartmentController;
import com.hospital.controllers.DoctorController;
import com.hospital.controllers.InvoiceController;
import com.hospital.controllers.MedicalHistoryController;
import com.hospital.controllers.MedicalRecordController;
import com.hospital.controllers.PatientController;
import com.hospital.controllers.UserController;


public class Main {
    public static void main(String[] args) {
        // Create Javalin app with CORS and logging
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> {
                cors.add(it -> {
                    it.anyHost(); // Allow all origins
                });
            });
            config.http.defaultContentType = "application/json";
        });

        // Global exception handling
        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500).json("Internal Server Error: " + e.getMessage());
            e.printStackTrace();
        });

        // Basic logging for each request
        app.before(ctx -> {
            System.out.println("Received " + ctx.method() + " -> " + ctx.fullUrl());
        });

        // Health check route
        app.get("/", ctx -> ctx.result("Hospital Management API is running."));

        // Controller route setup
        new AdminController().setupRoutes(app);
        new AppointmentController().setupRoutes(app);
        new DepartmentController().setupRoutes(app);
        new DoctorController().setupRoutes(app);
        new InvoiceController().setupRoutes(app);
        new MedicalHistoryController().setupRoutes(app);
        new MedicalRecordController().setupRoutes(app);
        new PatientController().setupRoutes(app);
        new UserController().setupRoutes(app);

        // Start server
        app.start(7000);

        // Graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down application...");
            app.stop();
        }));
    }
}
