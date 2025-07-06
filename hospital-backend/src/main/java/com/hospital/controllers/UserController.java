package com.hospital.controllers;

import com.hospital.daos.UserDAO;
import com.hospital.models.User;
import io.javalin.http.Context;
import java.util.List;

public class UserController {
    // GET /api/get_users
    public void getAllUsers(Context ctx) {
        List<User> users = UserDAO.getAllUsers();
        ctx.json(users);
    }

    // GET /api/get_users/{id}
    public void getUserById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        User user = UserDAO.getUserById(id);
        if (user != null) {
            ctx.json(user);
        } else {
            ctx.status(404).result("User not found");
        }
    }
}
