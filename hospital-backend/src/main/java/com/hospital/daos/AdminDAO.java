package com.hospital.daos;

import com.hospital.models.Admin;
import java.util.List;

public interface AdminDAO extends CrudDAO<Admin, Integer> {
    List<Admin> findSuperAdmins();
    List<Admin> findByDepartment(int departmentId);
}