package com.hospital.daos.impl;

import com.hospital.daos.AdminDAO;
import com.hospital.daos.UserDAO;
import com.hospital.models.Admin;
import com.hospital.utils.DatabaseUtil;
import com.hospital.models.User;
import java.sql.*;
import java.util.*;

public class AdminDAOImpl implements AdminDAO {
    private final Connection connection;
    private final UserDAO userDAO;

    public AdminDAOImpl() {
        this.connection = DatabaseUtil.getConnection();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public boolean create(Admin admin) {
        String sql = "INSERT INTO admins (user_id, is_super_admin, department_id, access_level) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, admin.getId());
            stmt.setBoolean(2, admin.isSuperAdmin());
            stmt.setObject(3, admin.getDepartmentId());
            stmt.setString(4, admin.getAccessLevel());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Admin> findById(Integer id) {
        String sql = "SELECT * FROM admins WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = userDAO.findById(id).orElseThrow();
                return Optional.of(new Admin(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getBoolean("is_super_admin"),
                    rs.getInt("department_id"),
                    rs.getString("access_level")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = userDAO.findById(userId).orElseThrow();
                admins.add(new Admin(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getBoolean("is_super_admin"),
                    rs.getInt("department_id"),
                    rs.getString("access_level")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public List<Admin> findSuperAdmins() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins WHERE is_super_admin = true";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = userDAO.findById(userId).orElseThrow();
                admins.add(new Admin(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    true,
                    rs.getInt("department_id"),
                    rs.getString("access_level")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public List<Admin> findByDepartment(int departmentId) {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins WHERE department_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = userDAO.findById(userId).orElseThrow();
                admins.add(new Admin(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt(),
                    rs.getBoolean("is_super_admin"),
                    rs.getInt("department_id"),
                    rs.getString("access_level")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public boolean update(Admin admin) {
        String sql = "UPDATE admins SET is_super_admin = ?, department_id = ?, access_level = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, admin.isSuperAdmin());
            stmt.setObject(2, admin.getDepartmentId());
            stmt.setString(3, admin.getAccessLevel());
            stmt.setInt(4, admin.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM admins WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}