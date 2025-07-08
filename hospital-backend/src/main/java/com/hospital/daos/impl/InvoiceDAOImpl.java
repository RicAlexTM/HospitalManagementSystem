package com.hospital.daos.impl;

import com.hospital.daos.InvoiceDAO;
import com.hospital.models.Invoice;
import com.hospital.utils.DatabaseUtil;
import java.sql.*;
import java.util.*;

public class InvoiceDAOImpl implements InvoiceDAO {
    private final Connection connection;

    public InvoiceDAOImpl() {
        this.connection = DatabaseUtil.getConnection();
    }

    @Override
    public boolean create(Invoice invoice) {
        String sql = "INSERT INTO invoices (appointment_id, amount, issued_date, payment_status, payment_method) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, invoice.getAppointmentId());
            stmt.setBigDecimal(2, invoice.getAmount());
            stmt.setTimestamp(3, invoice.getIssuedDate());
            stmt.setString(4, invoice.getPaymentStatus());
            stmt.setString(5, invoice.getPaymentMethod());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        invoice.setId(rs.getInt(1));
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
    public Optional<Invoice> findById(Integer id) {
        String sql = "SELECT * FROM invoices WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Invoice(
                    rs.getInt("id"),
                    rs.getInt("appointment_id"),
                    null, // Appointment can be loaded separately
                    rs.getBigDecimal("amount"),
                    rs.getTimestamp("issued_date"),
                    rs.getString("payment_status"),
                    rs.getString("payment_method")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Invoice> findAll() {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                invoices.add(new Invoice(
                    rs.getInt("id"),
                    rs.getInt("appointment_id"),
                    null,
                    rs.getBigDecimal("amount"),
                    rs.getTimestamp("issued_date"),
                    rs.getString("payment_status"),
                    rs.getString("payment_method")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    @Override
    public List<Invoice> findByPatientId(int patientId) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = """
            SELECT i.* FROM invoices i
            JOIN appointments a ON i.appointment_id = a.id
            WHERE a.patient_id = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                invoices.add(new Invoice(
                    rs.getInt("id"),
                    rs.getInt("appointment_id"),
                    null,
                    rs.getBigDecimal("amount"),
                    rs.getTimestamp("issued_date"),
                    rs.getString("payment_status"),
                    rs.getString("payment_method")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    @Override
    public boolean update(Invoice invoice) {
        String sql = "UPDATE invoices SET appointment_id = ?, amount = ?, issued_date = ?, payment_status = ?, payment_method = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, invoice.getAppointmentId());
            stmt.setBigDecimal(2, invoice.getAmount());
            stmt.setTimestamp(3, invoice.getIssuedDate());
            stmt.setString(4, invoice.getPaymentStatus());
            stmt.setString(5, invoice.getPaymentMethod());
            stmt.setInt(6, invoice.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePaymentStatus(int invoiceId, String status) {
        String sql = "UPDATE invoices SET payment_status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, invoiceId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM invoices WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}