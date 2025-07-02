package com.hospital.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.models.Appointment;
import com.hospital.utils.DatabaseUtil;

public class AppointmentDAO {

    public static List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = """
            SELECT * FROM appointments
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Appointment appointment = new Appointment(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null, // populate patient later if needed
                    rs.getInt("doctor_id"),
                    null, // populate doctor later if needed
                    rs.getTimestamp("appointment_date"),
                    rs.getString("status"),
                    rs.getString("reason"),
                    rs.getTimestamp("created_at")
                );

                appointments.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public static Appointment getAppointmentById(int id) {
        String query = "SELECT * FROM appointments WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Appointment(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    null,
                    rs.getInt("doctor_id"),
                    null,
                    rs.getTimestamp("appointment_date"),
                    rs.getString("status"),
                    rs.getString("reason"),
                    rs.getTimestamp("created_at")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean createAppointment(Appointment appointment) {
        String query = """
            INSERT INTO appointments (patient_id, doctor_id, appointment_date, status, reason)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setTimestamp(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getStatus());
            stmt.setString(5, appointment.getReason());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateAppointmentStatus(int id, String newStatus) {
        String query = "UPDATE appointments SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
