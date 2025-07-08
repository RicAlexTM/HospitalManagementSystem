package com.hospital.controllers;

import com.hospital.daos.DepartmentDAO;
import com.hospital.daos.impl.DepartmentDAOImpl;
import com.hospital.models.Department;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

public class DepartmentController {
    private final DepartmentDAO departmentDAO;

    public DepartmentController() {
        this.departmentDAO = new DepartmentDAOImpl();
    }

    public void setupRoutes(Javalin app) {
        // Get all departments
        app.get("/departments", this::getAllDepartments);
        
        // Get department by ID
        app.get("/departments/{id}", this::getDepartmentById);
        
        // Create new department
        app.post("/departments", this::createDepartment);
        
        // Update department
        app.put("/departments/{id}", this::updateDepartment);
        
        // Delete department
        app.delete("/departments/{id}", this::deleteDepartment);
        
        // Search departments by name
        app.get("/departments/search/{name}", this::searchDepartmentsByName);
    }

    private void getAllDepartments(Context ctx) {
        List<Department> departments = departmentDAO.findAll();
        ctx.json(departments).status(200);
    }

    private void getDepartmentById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        departmentDAO.findById(id).ifPresentOrElse(
            department -> ctx.json(department).status(200),
            () -> ctx.status(404).result("Department not found")
        );
    }

    private void createDepartment(Context ctx) {
        Department department = ctx.bodyAsClass(Department.class);
        if (departmentDAO.create(department)) {
            ctx.status(201).json(department);
        } else {
            ctx.status(400).result("Failed to create department");
        }
    }

    private void updateDepartment(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Department updatedDepartment = ctx.bodyAsClass(Department.class);
        updatedDepartment.setId(id);
        
        if (departmentDAO.update(updatedDepartment)) {
            ctx.status(200).json(updatedDepartment);
        } else {
            ctx.status(400).result("Failed to update department");
        }
    }

    private void deleteDepartment(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (departmentDAO.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Department not found");
        }
    }

    private void searchDepartmentsByName(Context ctx) {
        String name = ctx.pathParam("name");
        List<Department> departments = departmentDAO.findByName(name);
        ctx.json(departments).status(200);
    }
}