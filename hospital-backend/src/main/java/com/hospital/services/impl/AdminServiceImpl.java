package com.hospital.services.impl;

import java.util.List;
import java.util.Optional;

import com.hospital.daos.AdminDAO;
import com.hospital.daos.impl.AdminDAOImpl;
import com.hospital.models.Admin;
import com.hospital.services.AdminService;

public class AdminServiceImpl implements AdminService {
    private final AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public List<Admin> getAllAdmins() {
        return adminDAO.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(int id) {
        return adminDAO.findById(id);
    }

    @Override
    public boolean createAdmin(Admin admin) {
        return adminDAO.create(admin);
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return adminDAO.update(admin);
    }

    @Override
    public boolean deleteAdmin(int id) {
        return adminDAO.delete(id);
    }

    @Override
    public List<Admin> getSuperAdmins() {
        return adminDAO.findSuperAdmins();
    }

    
    @Override
    public List<Admin> getAdminsByDepartment(int deptId) {
        return adminDAO.findByDepartment(deptId);
    }
}