package com.hospital.services;

import java.util.List;

import com.hospital.daos.AdminDAO;
import com.hospital.models.Admin;

public class AdminService {

    public List<Admin> getAllAdmins() {
        return AdminDAO.getAllAdmins();
    }

    public Admin getAdminByUserId(int userId) {
        return AdminDAO.getAdminByUserId(userId);
    }

    public boolean registerAdmin(Admin admin) {
        return AdminDAO.createAdmin(admin);
    }
}
