package com.hospital.services;


import com.hospital.models.Invoice;
import java.util.Optional;

import java.util.List;

public interface InvoiceService {
    List<Invoice> findAll();
    Optional<Invoice> findById(int id);
    boolean create(Invoice invoice);
    boolean update(Invoice invoice);
    boolean delete(int id);
    List<Invoice> findByPatientId(int patientId);
    boolean updatePaymentStatus(int id, String status);
}
