package com.hospital.services.impl;

import com.hospital.models.MedicalHistory;
import com.hospital.daos.MedicalHistoryDAO;
import com.hospital.daos.impl.MedicalHistoryDAOImpl;
import com.hospital.services.MedicalHistoryService;
import java.util.List;
import java.util.Optional;


class MedicalHistoryServiceImpl implements MedicalHistoryService {
    private final MedicalHistoryDAO dao = new MedicalHistoryDAOImpl();

    public List<MedicalHistory> findAll() { return dao.findAll(); }
    public Optional<MedicalHistory> findById(int id) { return dao.findById(id); }
    public boolean create(MedicalHistory mh) { return dao.create(mh); }
    public boolean update(MedicalHistory mh) { return dao.update(mh); }
    public boolean delete(int id) { return dao.delete(id); }
    public List<MedicalHistory> findByPatientId(int pid) { return dao.findByPatientId(pid); }
    public List<MedicalHistory> findByCondition(String cond) { return dao.findByCondition(cond); }
}