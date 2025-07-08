package com.hospital.services.impl;

import com.hospital.models.MedicalRecord;
import com.hospital.daos.MedicalRecordDAO;
import com.hospital.daos.impl.MedicalRecordDAOImpl;
import com.hospital.services.MedicalRecordService;  
import java.util.List;
import java.util.Optional;


class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordDAO dao = new MedicalRecordDAOImpl();

    public List<MedicalRecord> findAll() { return dao.findAll(); }
    public Optional<MedicalRecord> findById(int id) { return dao.findById(id); }
    public boolean create(MedicalRecord rec) { return dao.create(rec); }
    public boolean update(MedicalRecord rec) { return dao.update(rec); }
    public boolean delete(int id) { return dao.delete(id); }
    public List<MedicalRecord> findByPatientId(int pid) { return dao.findByPatientId(pid); }
    public List<MedicalRecord> findByDoctorId(int did) { return dao.findByDoctorId(did); }
}
