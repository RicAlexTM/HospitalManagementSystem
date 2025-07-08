package com.hospital.daos.impl;

import com.hospital.daos.AppointmentDAO;
import com.hospital.models.Appointment;
import com.hospital.utils.DatabaseUtil;
import java.sql.*;
import java.util.*;

public class AppointmentDAOImpl implements AppointmentDAO {
    private final Connection connection;

    public AppointmentDAOImpl() {
        this.connection = DatabaseUtil.getConnection();
    }

    @Override
    public boolean create(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status, reason) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setTimestamp(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getStatus());
            stmt.setString(5, appointment.getReason());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        appointment.setId(rs.getInt(1));
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
    public Optional<Appointment> findById(Integer id) {
        String sql = "SELECT * FROM appointments WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Appointment(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null, // Patient can be loaded separately if needed
                    rs.getInt("doctor_id"),
                    null, // Doctor can be loaded separately if needed
                    rs.getTimestamp("appointment_date"),
                    rs.getString("status"),
                    rs.getString("reason"),
                    rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                appointments.add(new Appointment(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null,
                    rs.getInt("doctor_id"),
                    null,
                    rs.getTimestamp("appointment_date"),
                    rs.getString("status"),
                    rs.getString("reason"),
                    rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public List<Appointment> findByPatientId(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null,
                    rs.getInt("doctor_id"),
                    null,
                    rs.getTimestamp("appointment_date"),
                    rs.getString("status"),
                    rs.getString("reason"),
                    rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public List<Appointment> findByDoctorId(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE doctor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null,
                    rs.getInt("doctor_id"),
                    null,
                    rs.getTimestamp("appointment_date"),
                    rs.getString("status"),
                    rs.getString("reason"),
                    rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public boolean update(Appointment appointment) {
        String sql = "UPDATE appointments SET patient_id = ?, doctor_id = ?, appointment_date = ?, status = ?, reason = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setTimestamp(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getStatus());
            stmt.setString(5, appointment.getReason());
            stmt.setInt(6, appointment.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateStatus(int appointmentId, String status) {
        String sql = "UPDATE appointments SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, appointmentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}