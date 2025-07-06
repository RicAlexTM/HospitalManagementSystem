package com.hospital.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.models.Admin;
import com.hospital.models.Department;
import com.hospital.models.User;
import com.hospital.utils.DatabaseUtil;

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
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("role"),
                    rs.getTimestamp("created_at")
                );

                Department department = rs.getObject("d.id") != null
                        ? new Department(
                            rs.getInt("d.id"),
                            rs.getString("d.name"),
                            rs.getString("d.description"),
                            rs.getTimestamp("d.created_at"))
                        : null;

                Admin admin = new Admin(
                    rs.getInt("user_id"),
                    user,
                    rs.getBoolean("is_super_admin"),
                    rs.getInt("department_id"),
                    department,
                    rs.getString("access_level")
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
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("role"),
                    rs.getTimestamp("created_at")
                );

                Department department = rs.getObject("d.id") != null
                        ? new Department(
                            rs.getInt("d.id"),
                            rs.getString("d.name"),
                            rs.getString("d.description"),
                            rs.getTimestamp("d.created_at"))
                        : null;

                return new Admin(
                    rs.getInt("user_id"),
                    user,
                    rs.getBoolean("is_super_admin"),
                    rs.getInt("department_id"),
                    department,
                    rs.getString("access_level")
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

            stmt.setInt(1, admin.getUser().getId());
            stmt.setBoolean(2, admin.isSuperAdmin());
            stmt.setInt(3, admin.getDepartmentId());
            stmt.setString(4, admin.getAccessLevel());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateAdmin(Admin admin) {
        String query = """
            UPDATE admins
            SET is_super_admin = ?, department_id = ?, access_level = ?
            WHERE user_id = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, admin.isSuperAdmin());
            stmt.setInt(2, admin.getDepartmentId());
            stmt.setString(3, admin.getAccessLevel());
            stmt.setInt(4, admin.getUserId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteAdmin(int userId) {
        String query = """
            DELETE FROM admins
            WHERE user_id = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
