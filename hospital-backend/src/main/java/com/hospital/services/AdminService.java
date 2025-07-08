package com.hospital.services;

import java.util.List;
import java.util.Optional;

import com.hospital.models.Admin;

public interface AdminService {
    List<Admin> getAllAdmins();
    Optional<Admin> getAdminById(int id);
    boolean createAdmin(Admin admin);
    boolean updateAdmin(Admin admin);
    boolean deleteAdmin(int id);
    List<Admin> getSuperAdmins();
    List<Admin> getAdminsByDepartment(int deptId);
}