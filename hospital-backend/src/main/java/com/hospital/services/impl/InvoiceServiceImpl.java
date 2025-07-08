package com.hospital.services.impl;

import com.hospital.models.Invoice;
import com.hospital.daos.InvoiceDAO;
import com.hospital.daos.impl.InvoiceDAOImpl;
import com.hospital.services.InvoiceService;
import java.util.List;
import java.util.Optional;


class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceDAO dao = new InvoiceDAOImpl();

    public List<Invoice> findAll() { return dao.findAll(); }
    public Optional<Invoice> findById(int id) { return dao.findById(id); }
    public boolean create(Invoice inv) { return dao.create(inv); }
    public boolean update(Invoice inv) { return dao.update(inv); }
    public boolean delete(int id) { return dao.delete(id); }
    public List<Invoice> findByPatientId(int pid) { return dao.findByPatientId(pid); }
    public boolean updatePaymentStatus(int id, String status) { return dao.updatePaymentStatus(id, status); }
}
