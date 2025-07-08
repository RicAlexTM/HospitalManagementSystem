package com.hospital.daos.impl;

import com.hospital.daos.PatientDAO;
import com.hospital.daos.UserDAO;
import com.hospital.models.Patient;
import com.hospital.utils.DatabaseUtil;
import java.sql.*;
import java.util.*;

public class PatientDAOImpl implements PatientDAO {
    private final Connection connection;
    private final UserDAO userDAO;

    public PatientDAOImpl() {
        this.connection = DatabaseUtil.getConnection();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public boolean create(Patient patient) {
        String sql = "INSERT INTO patients (user_id, date_of_birth, blood_type) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            stmt.setDate(2, patient.getDateOfBirth());
            stmt.setString(3, patient.getBloodType());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Patient> findById(Integer id) {
        String sql = "SELECT * FROM patients WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = userDAO.findById(id).orElseThrow();
                return Optional.of(new Patient(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getDate("date_of_birth"),
                    rs.getString("blood_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = userDAO.findById(userId).orElseThrow();
                patients.add(new Patient(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getDate("date_of_birth"),
                    rs.getString("blood_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> findByBloodType(String bloodType) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE blood_type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bloodType);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = userDAO.findById(userId).orElseThrow();
                patients.add(new Patient(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getDate("date_of_birth"),
                    rs.getString("blood_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public boolean update(Patient patient) {
        String sql = "UPDATE patients SET date_of_birth = ?, blood_type = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, patient.getDateOfBirth());
            stmt.setString(2, patient.getBloodType());
            stmt.setInt(3, patient.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM patients WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}