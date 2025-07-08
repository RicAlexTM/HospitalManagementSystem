package com.hospital.controllers;

import java.util.List;

import com.hospital.daos.UserDAO;
import com.hospital.daos.impl.UserDAOImpl;
import com.hospital.models.User;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {
    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAOImpl();
    }

    public void setupRoutes(Javalin app) {
        // Get all users
        app.get("/users", this::getAllUsers);
        
        // Get user by ID
        app.get("/users/{id}", this::getUserById);
        
        // Create new user
        app.post("/users", this::createUser);
        
        // Update user
        app.put("/users/{id}", this::updateUser);
        
        // Delete user
        app.delete("/users/{id}", this::deleteUser);
        
        // User login
        app.post("/users/login", this::loginUser);
    }

    private void getAllUsers(Context ctx) {
        List<User> users = userDAO.findAll();
        ctx.json(users).status(200);
    }

    private void getUserById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        userDAO.findById(id).ifPresentOrElse(
            user -> ctx.json(user).status(200),
            () -> ctx.status(404).result("User not found")
        );
    }

    private void createUser(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        if (userDAO.create(user)) {
            ctx.status(201).json(user);
        } else {
            ctx.status(400).result("Failed to create user");
        }
    }

    private void updateUser(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        User updatedUser = ctx.bodyAsClass(User.class);
        updatedUser.setId(id);
        
        if (userDAO.update(updatedUser)) {
            ctx.status(200).json(updatedUser);
        } else {
            ctx.status(400).result("Failed to update user");
        }
    }

    private void deleteUser(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (userDAO.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("User not found");
        }
    }

    private void loginUser(Context ctx) {
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        
        if (userDAO.validateCredentials(email, password)) {
            ctx.status(200).result("Login successful");
        } else {
            ctx.status(401).result("Invalid credentials");
        }
    }
}