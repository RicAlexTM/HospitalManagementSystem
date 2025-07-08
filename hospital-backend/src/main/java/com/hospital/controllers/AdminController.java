package com.hospital.controllers;

import java.util.List;

import com.hospital.daos.AdminDAO;
import com.hospital.daos.impl.AdminDAOImpl;
import com.hospital.models.Admin;



import io.javalin.Javalin;
import io.javalin.http.Context;

public class AdminController {
    private final AdminDAO adminDAO;

    public AdminController() {
        this.adminDAO = new AdminDAOImpl();
    }

    public void setupRoutes(Javalin app) {
        // Get all admins
        app.get("/admins", this::getAllAdmins);
        
        // Get admin by ID
        app.get("/admins/{id}", this::getAdminById);
        
        // Create new admin
        app.post("/admins", this::createAdmin);
        
        // Update admin
        app.put("/admins/{id}", this::updateAdmin);
        
        // Delete admin
        app.delete("/admins/{id}", this::deleteAdmin);
        
        // Get super admins
        app.get("/admins/super", this::getSuperAdmins);
        
        // Get admins by department
        app.get("/departments/{deptId}/admins", this::getAdminsByDepartment);
    }

    private void getAllAdmins(Context ctx) {
        List<Admin> admins = adminDAO.findAll();
        ctx.json(admins).status(200);
    }

    private void getAdminById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        adminDAO.findById(id).ifPresentOrElse(
            admin -> ctx.json(admin).status(200),
            () -> ctx.status(404).result("Admin not found")
        );
    }

    private void createAdmin(Context ctx) {
        Admin admin = ctx.bodyAsClass(Admin.class);
        if (adminDAO.create(admin)) {
            ctx.status(201).json(admin);
        } else {
            ctx.status(400).result("Failed to create admin");
        }
    }

    private void updateAdmin(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Admin updatedAdmin = ctx.bodyAsClass(Admin.class);
        updatedAdmin.setId(id);
        
        if (adminDAO.update(updatedAdmin)) {
            ctx.status(200).json(updatedAdmin);
        } else {
            ctx.status(400).result("Failed to update admin");
        }
    }

    private void deleteAdmin(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (adminDAO.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Admin not found");
        }
    }

    private void getSuperAdmins(Context ctx) {
        List<Admin> admins = adminDAO.findSuperAdmins();
        ctx.json(admins).status(200);
    }

    private void getAdminsByDepartment(Context ctx) {
        int deptId = Integer.parseInt(ctx.pathParam("deptId"));
        List<Admin> admins = adminDAO.findByDepartment(deptId);
        ctx.json(admins).status(200);
    }
}