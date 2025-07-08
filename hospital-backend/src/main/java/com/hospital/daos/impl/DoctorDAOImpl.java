package com.hospital.daos.impl;

import com.hospital.daos.DoctorDAO;
import com.hospital.daos.UserDAO;
import com.hospital.models.Doctor;
import com.hospital.utils.DatabaseUtil;
import java.sql.*;
import java.util.*;

public class DoctorDAOImpl implements DoctorDAO {
    private final Connection connection;
    private final UserDAO userDAO;

    public DoctorDAOImpl() {
        this.connection = DatabaseUtil.getConnection();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public boolean create(Doctor doctor) {
        String sql = "INSERT INTO doctors (user_id, department_id, specialization, license_number, super_user) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctor.getId());
            stmt.setInt(2, doctor.getDepartmentId());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setString(4, doctor.getLicenseNumber());
            stmt.setBoolean(5, doctor.isSuperUser());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Doctor> findById(Integer id) {
        String sql = "SELECT * FROM doctors WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = userDAO.findById(id).orElseThrow();
                return Optional.of(new Doctor(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getInt("department_id"),
                    rs.getString("specialization"),
                    rs.getString("license_number"),
                    rs.getBoolean("super_user")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = userDAO.findById(userId).orElseThrow();
                doctors.add(new Doctor(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getInt("department_id"),
                    rs.getString("specialization"),
                    rs.getString("license_number"),
                    rs.getBoolean("super_user")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public List<Doctor> findByDepartment(int departmentId) {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors WHERE department_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = userDAO.findById(userId).orElseThrow();
                doctors.add(new Doctor(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getInt("department_id"),
                    rs.getString("specialization"),
                    rs.getString("license_number"),
                    rs.getBoolean("super_user")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public List<Doctor> findBySpecialization(String specialization) {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors WHERE specialization = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, specialization);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = userDAO.findById(userId).orElseThrow();
                doctors.add(new Doctor(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getInt("department_id"),
                    rs.getString("specialization"),
                    rs.getString("license_number"),
                    rs.getBoolean("super_user")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public boolean update(Doctor doctor) {
        String sql = "UPDATE doctors SET department_id = ?, specialization = ?, license_number = ?, super_user = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctor.getDepartmentId());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setString(3, doctor.getLicenseNumber());
            stmt.setBoolean(4, doctor.isSuperUser());
            stmt.setInt(5, doctor.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM doctors WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}