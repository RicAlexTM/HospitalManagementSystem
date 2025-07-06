package com.hospital.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.models.Department;
import com.hospital.models.Doctor;
import com.hospital.models.User;
import com.hospital.utils.DatabaseUtil;

public class DoctorDAO {

    public static List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = """
            SELECT d.*, u.*, dept.*
            FROM doctors d
            JOIN users u ON d.user_id = u.id
            LEFT JOIN departments dept ON d.department_id = dept.id
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

                Department dept = rs.getObject("dept.id") != null
                        ? new Department(
                            rs.getInt("dept.id"),
                            rs.getString("dept.name"),
                            rs.getString("dept.description"),
                            rs.getTimestamp("dept.created_at"))
                        : null;

                Doctor doctor = new Doctor(
                    rs.getInt("user_id"),
                    user,
                    rs.getInt("department_id"),
                    dept,
                    rs.getString("specialization"),
                    rs.getString("license_number"),
                    rs.getBoolean("super_user")
                );

                doctors.add(doctor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    public static Doctor getDoctorById(int userId) {
        String query = """
            SELECT d.*, u.*, dept.*
            FROM doctors d
            JOIN users u ON d.user_id = u.id
            LEFT JOIN departments dept ON d.department_id = dept.id
            WHERE d.user_id = ?
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

                Department dept = rs.getObject("dept.id") != null
                        ? new Department(
                            rs.getInt("dept.id"),
                            rs.getString("dept.name"),
                            rs.getString("dept.description"),
                            rs.getTimestamp("dept.created_at"))
                        : null;

                return new Doctor(
                    rs.getInt("user_id"),
                    user,
                    rs.getInt("department_id"),
                    dept,
                    rs.getString("specialization"),
                    rs.getString("license_number"),
                    rs.getBoolean("super_user")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean createDoctor(Doctor doctor) {
        String query = """
            INSERT INTO doctors (user_id, department_id, specialization, license_number, super_user)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, doctor.getUserId());
            stmt.setObject(2, doctor.getDepartmentId());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setString(4, doctor.getLicenseNumber());
            stmt.setBoolean(5, doctor.isSuperUser());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
