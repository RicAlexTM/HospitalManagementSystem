package com.hospital.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.models.Patient;
import com.hospital.models.User;
import com.hospital.utils.DatabaseUtil;

public class PatientDAO {

    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();

        String query = "SELECT * FROM patients p JOIN users u ON p.user_id = u.id";
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

                Patient patient = new Patient(
                    rs.getInt("user_id"),
                    user,
                    rs.getDate("date_of_birth"),
                    rs.getString("blood_type")
                );

                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public static Patient getPatientById(int userId) {
        String query = "SELECT * FROM patients p JOIN users u ON p.user_id = u.id WHERE p.user_id = ?";
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

                return new Patient(
                    rs.getInt("user_id"),
                    user,
                    rs.getDate("date_of_birth"),
                    rs.getString("blood_type")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean createPatient(Patient patient) {
        String query = "INSERT INTO patients (user_id, date_of_birth, blood_type) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, patient.getUserId());
            stmt.setDate(2, patient.getDateOfBirth());
            stmt.setString(3, patient.getBloodType());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
