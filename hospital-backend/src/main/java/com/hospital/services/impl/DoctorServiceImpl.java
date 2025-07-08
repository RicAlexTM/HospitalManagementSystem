package com.hospital.services.impl;

import com.hospital.daos.DoctorDAO;
import com.hospital.daos.impl.DoctorDAOImpl;
import com.hospital.models.Doctor;
import com.hospital.services.DoctorService;
import java.util.List;
import java.util.Optional;


class DoctorServiceImpl implements DoctorService {
    private final DoctorDAO dao = new DoctorDAOImpl();

    @Override
    public List<Doctor> findAll() { return dao.findAll(); }

    @Override
    public Optional<Doctor> findById(int id) { return dao.findById(id); }

    @Override
    public boolean create(Doctor doc) { return dao.create(doc); }

    @Override
    public boolean update(Doctor doc) { return dao.update(doc); }

    @Override
    public boolean delete(int id) { return dao.delete(id); }

    @Override
    public List<Doctor> findByDepartment(int deptId) { return dao.findByDepartment(deptId); }

    @Override
    public List<Doctor> findBySpecialization(String spec) { return dao.findBySpecialization(spec); }
}
