package com.hospital.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.models.Invoice;
import com.hospital.utils.DatabaseUtil;

public class InvoiceDAO {

    public static List<Invoice> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        String query = "SELECT * FROM invoices";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int appointmentId = rs.getInt("appointment_id");
                // Fetch the Appointment object using AppointmentDAO (assuming it exists)
                com.hospital.models.Appointment appointment = com.hospital.daos.AppointmentDAO.getAppointmentById(appointmentId);
                Invoice invoice = new Invoice(
                    rs.getInt("id"),
                    appointmentId,
                    appointment,
                    rs.getBigDecimal("amount"),
                    rs.getTimestamp("issued_date"),
                    rs.getString("payment_status"),
                    rs.getString("payment_method")
                );

                invoices.add(invoice);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoices;
    }

    public static Invoice getInvoiceById(int id) {
        String query = "SELECT * FROM invoices WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int appointmentId = rs.getInt("appointment_id");
                    com.hospital.models.Appointment appointment = com.hospital.daos.AppointmentDAO.getAppointmentById(appointmentId);
                    return new Invoice(
                        rs.getInt("id"),
                        appointmentId,
                        appointment,
                        rs.getBigDecimal("amount"),
                        rs.getTimestamp("issued_date"),
                        rs.getString("payment_status"),
                        rs.getString("payment_method")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean createInvoice(Invoice invoice) {
        String query = """
            INSERT INTO invoices (appointment_id, amount, issued_date, payment_status, payment_method)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, invoice.getAppointmentId());
            stmt.setBigDecimal(2, invoice.getAmount());
            stmt.setTimestamp(3, invoice.getIssuedDate());
            stmt.setString(4, invoice.getPaymentStatus());
            stmt.setString(5, invoice.getPaymentMethod());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updatePaymentStatus(int id, String newStatus) {
        String query = "UPDATE invoices SET payment_status = ? WHERE id = ?";

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
