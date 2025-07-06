package com.hospital.controllers;

import com.hospital.daos.UserDAO;
import com.hospital.models.User;

import io.javalin.http.Context;

public class AuthController {
    public void login(Context ctx) {
        try {
            User loginRequest = ctx.bodyAsClass(User.class);
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            // Debug print
            System.out.println("LOGIN ATTEMPT: email=" + email + ", password=" + password);

            // Find user by email
            User user = UserDAO.getUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                ctx.status(200).json(user); // In production, do not return password!
            } else {
                ctx.status(401).result("Invalid email or password");
            }
        } catch (Exception e) {
            ctx.status(500).result("Error processing login");
        }
    }

    public void register(Context ctx) {
        try {
            User newUser = ctx.bodyAsClass(User.class);
            boolean created = UserDAO.createUser(newUser);
            if (created) {
                ctx.status(201).result("Registration successful");
            } else {
                ctx.status(400).result("Registration failed");
            }
        } catch (Exception e) {
            ctx.status(500).result("Error processing registration");
        }
    }
}
