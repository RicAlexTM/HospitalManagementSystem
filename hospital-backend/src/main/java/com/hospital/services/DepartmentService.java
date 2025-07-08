package com.hospital.services;

import com.hospital.models.Department;
import java.util.List;  
import java.util.Optional;


public interface DepartmentService {
    List<Department> findAll();
    Optional<Department> findById(int id);
    boolean create(Department department);
    boolean update(Department department);
    boolean delete(int id);
    List<Department> findByName(String name);
}
