package com.hospital.daos;

import com.hospital.models.Department;
import java.util.List;

public interface DepartmentDAO extends CrudDAO<Department, Integer> {
    List<Department> findByName(String name);
}