package com.hospital.daos;

import com.hospital.models.Admin;
import com.hospital.models.Department;
import com.hospital.models.User;
import com.hospital.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public static List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String query = """
            SELECT a.*, u.*, d.*
            FROM admins a
            JOIN users u ON a.user_id = u.id
            LEFT JOIN departments d ON a.department_id = d.id
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                    rs.getInt("u.id"),
                    rs.getString("u.name"),
                    rs.getString("u.email"),
                    rs.getString("u.password"),
                    rs.getString("u.phone"),
                    rs.getString("u.role"),
                    rs.getTimestamp("u.created_at")
                );

                Department department = rs.getObject("d.id") != null
                        ? new Department(
                            rs.getInt("d.id"),
                            rs.getString("d.name"),
                            rs.getString("d.description"),
                            rs.getTimestamp("d.created_at"))
                        : null;

                Admin admin = new Admin(
                    rs.getInt("a.user_id"),
                    user,
                    rs.getBoolean("a.is_super_admin"),
                    rs.getInt("a.department_id"),
                    department,
                    rs.getString("a.access_level")
                );

                admins.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    public static Admin getAdminByUserId(int userId) {
        String query = """
            SELECT a.*, u.*, d.*
            FROM admins a
            JOIN users u ON a.user_id = u.id
            LEFT JOIN departments d ON a.department_id = d.id
            WHERE a.user_id = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User(
                    rs.getInt("u.id"),
                    rs.getString("u.name"),
                    rs.getString("u.email"),
                    rs.getString("u.password"),
                    rs.getString("u.phone"),
                    rs.getString("u.role"),
                    rs.getTimestamp("u.created_at")
                );

                Department department = rs.getObject("d.id") != null
                        ? new Department(
                            rs.getInt("d.id"),
                            rs.getString("d.name"),
                            rs.getString("d.description"),
                            rs.getTimestamp("d.created_at"))
                        : null;

                return new Admin(
                    rs.getInt("a.user_id"),
                    user,
                    rs.getBoolean("a.is_super_admin"),
                    rs.getInt("a.department_id"),
                    department,
                    rs.getString("a.access_level")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean createAdmin(Admin admin) {
        String query = """
            INSERT INTO admins (user_id, is_super_admin, department_id, access_level)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
