package com.hospital.services.impl;

import com.hospital.daos.DepartmentDAO;
import com.hospital.daos.impl.DepartmentDAOImpl;
import com.hospital.models.Department;
import com.hospital.services.DepartmentService;
import java.util.List;
import java.util.Optional;


class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDAO dao = new DepartmentDAOImpl();

    @Override
    public List<Department> findAll() { return dao.findAll(); }

    @Override
    public Optional<Department> findById(int id) { return dao.findById(id); }

    @Override
    public boolean create(Department dept) { return dao.create(dept); }

    @Override
    public boolean update(Department dept) { return dao.update(dept); }

    @Override
    public boolean delete(int id) { return dao.delete(id); }

    @Override
    public List<Department> findByName(String name) { return dao.findByName(name); }
}
