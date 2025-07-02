package com.hospital.controllers;

import com.hospital.models.Admin;
import com.hospital.services.AdminService;
import io.javalin.http.Context;

import java.util.List;

public class AdminController {

    private final AdminService adminService = new AdminService();

    public void getAllAdmins(Context ctx) {
        List<Admin> admins = adminService.getAllAdmins();
        ctx.json(admins);
    }

    public void getAdminByUserId(Context ctx) {
        int userId = Integer.parseInt(ctx.pathParam("userId"));
        Admin admin = adminService.getAdminByUserId(userId);

        if (admin != null) {
            ctx.json(admin);
        } else {
            ctx.status(404).result("Admin not found");
        }
    }

    public void registerAdmin(Context ctx) {
        try {
            Admin admin = ctx.bodyAsClass(Admin.class);
            boolean created = adminService.registerAdmin(admin);

            if (created) {
                ctx.status(201).result("Admin registered successfully");
            } else {
                ctx.status(400).result("Failed to register admin");
            }

        } catch (Exception e) {
            ctx.status(500).result("Invalid request");
        }
    }
}
