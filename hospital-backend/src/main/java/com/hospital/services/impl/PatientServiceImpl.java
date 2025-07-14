package com.hospital.services.impl;

import com.hospital.models.Patient;
import com.hospital.daos.PatientDAO;
import com.hospital.daos.impl.PatientDAOImpl;
import com.hospital.services.PatientService;
import java.util.List;
import java.util.Optional;


class PatientServiceImpl implements PatientService {
    private final PatientDAO dao = new PatientDAOImpl();

    @Override
    public List<Patient> findAll() { 
        return dao.findAll(); 
    }
    public Optional<Patient> findById(int id) {
         return dao.findById(id); 
        }
    public boolean create(Patient p) { 
        return dao.create(p); 
    }
    public boolean update(Patient p) { 
        return dao.update(p); 
    }
    public boolean delete(int id) { 
        return dao.delete(id); 
    }
    public List<Patient> findByBloodType(String type) { 
        return dao.findByBloodType(type); 
    }
}
