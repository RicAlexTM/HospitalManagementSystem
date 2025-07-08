package com.hospital.daos.impl;

import com.hospital.daos.MedicalRecordDAO;
import com.hospital.models.MedicalRecord;
import com.hospital.utils.DatabaseUtil;
import java.sql.*;
import java.util.*;

public class MedicalRecordDAOImpl implements MedicalRecordDAO {
    private final Connection connection;

    public MedicalRecordDAOImpl() {
        this.connection = DatabaseUtil.getConnection();
    }

    @Override
    public boolean create(MedicalRecord record) {
        String sql = "INSERT INTO medical_records (appointment_id, patient_id, doctor_id, diagnosis, treatment, prescription, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, record.getAppointmentId());
            stmt.setInt(2, record.getPatientId());
            stmt.setInt(3, record.getDoctorId());
            stmt.setString(4, record.getDiagnosis());
            stmt.setString(5, record.getTreatment());
            stmt.setString(6, record.getPrescription());
            stmt.setString(7, record.getNotes());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        record.setId(rs.getInt(1));
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
    public Optional<MedicalRecord> findById(Integer id) {
        String sql = "SELECT * FROM medical_records WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new MedicalRecord(
                    rs.getInt("id"),
                    rs.getInt("appointment_id"),
                    null, // Appointment can be loaded separately
                    rs.getInt("patient_id"),
                    null, // Patient can be loaded separately
                    rs.getInt("doctor_id"),
                    null, // Doctor can be loaded separately
                    rs.getString("diagnosis"),
                    rs.getString("treatment"),
                    rs.getString("prescription"),
                    rs.getString("notes"),
                    rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<MedicalRecord> findAll() {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM medical_records";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add(new MedicalRecord(
                    rs.getInt("id"),
                    rs.getInt("appointment_id"),
                    null,
                    rs.getInt("patient_id"),
                    null,
                    rs.getInt("doctor_id"),
                    null,
                    rs.getString("diagnosis"),
                    rs.getString("treatment"),
                    rs.getString("prescription"),
                    rs.getString("notes"),
                    rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<MedicalRecord> findByPatientId(int patientId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM medical_records WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                records.add(new MedicalRecord(
                    rs.getInt("id"),
                    rs.getInt("appointment_id"),
                    null,
                    rs.getInt("patient_id"),
                    null,
                    rs.getInt("doctor_id"),
                    null,
                    rs.getString("diagnosis"),
                    rs.getString("treatment"),
                    rs.getString("prescription"),
                    rs.getString("notes"),
                    rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<MedicalRecord> findByDoctorId(int doctorId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM medical_records WHERE doctor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                records.add(new MedicalRecord(
                    rs.getInt("id"),
                    rs.getInt("appointment_id"),
                    null,
                    rs.getInt("patient_id"),
                    null,
                    rs.getInt("doctor_id"),
                    null,
                    rs.getString("diagnosis"),
                    rs.getString("treatment"),
                    rs.getString("prescription"),
                    rs.getString("notes"),
                    rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public boolean update(MedicalRecord record) {
        String sql = "UPDATE medical_records SET appointment_id = ?, patient_id = ?, doctor_id = ?, diagnosis = ?, treatment = ?, prescription = ?, notes = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, record.getAppointmentId());
            stmt.setInt(2, record.getPatientId());
            stmt.setInt(3, record.getDoctorId());
            stmt.setString(4, record.getDiagnosis());
            stmt.setString(5, record.getTreatment());
            stmt.setString(6, record.getPrescription());
            stmt.setString(7, record.getNotes());
            stmt.setInt(8, record.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM medical_records WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}