package com.hospital.daos.impl;

import com.hospital.daos.MedicalHistoryDAO;
import com.hospital.models.MedicalHistory;
import com.hospital.utils.DatabaseUtil;
import java.sql.*;
import java.util.*;

public class MedicalHistoryDAOImpl implements MedicalHistoryDAO {
    private final Connection connection;

    public MedicalHistoryDAOImpl() {
        this.connection = DatabaseUtil.getConnection();
    }

    @Override
    public boolean create(MedicalHistory history) {
        String sql = "INSERT INTO medical_histories (patient_id, condition_name, diagnosis_date, treatment, notes) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, history.getPatientId());
            stmt.setString(2, history.getConditionName());
            stmt.setDate(3, history.getDiagnosisDate());
            stmt.setString(4, history.getTreatment());
            stmt.setString(5, history.getNotes());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        history.setId(rs.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<MedicalHistory> findById(Integer id) {
        String sql = "SELECT * FROM medical_histories WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new MedicalHistory(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null, // Patient can be loaded separately
                    rs.getString("condition_name"),
                    rs.getDate("diagnosis_date"),
                    rs.getString("treatment"),
                    rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<MedicalHistory> findAll() {
        List<MedicalHistory> histories = new ArrayList<>();
        String sql = "SELECT * FROM medical_histories";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                histories.add(new MedicalHistory(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null,
                    rs.getString("condition_name"),
                    rs.getDate("diagnosis_date"),
                    rs.getString("treatment"),
                    rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }

    @Override
    public List<MedicalHistory> findByPatientId(int patientId) {
        List<MedicalHistory> histories = new ArrayList<>();
        String sql = "SELECT * FROM medical_histories WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                histories.add(new MedicalHistory(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null,
                    rs.getString("condition_name"),
                    rs.getDate("diagnosis_date"),
                    rs.getString("treatment"),
                    rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }

    @Override
    public List<MedicalHistory> findByCondition(String conditionName) {
        List<MedicalHistory> histories = new ArrayList<>();
        String sql = "SELECT * FROM medical_histories WHERE condition_name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + conditionName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                histories.add(new MedicalHistory(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null,
                    rs.getString("condition_name"),
                    rs.getDate("diagnosis_date"),
                    rs.getString("treatment"),
                    rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histories;
    }

    @Override
    public boolean update(MedicalHistory history) {
        String sql = "UPDATE medical_histories SET patient_id = ?, condition_name = ?, diagnosis_date = ?, treatment = ?, notes = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, history.getPatientId());
            stmt.setString(2, history.getConditionName());
            stmt.setDate(3, history.getDiagnosisDate());
            stmt.setString(4, history.getTreatment());
            stmt.setString(5, history.getNotes());
            stmt.setInt(6, history.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM medical_histories WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}