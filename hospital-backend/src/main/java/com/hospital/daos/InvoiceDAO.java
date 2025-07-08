package com.hospital.daos;

import com.hospital.models.Invoice;
import java.util.List;

public interface InvoiceDAO extends CrudDAO<Invoice, Integer> {
    List<Invoice> findByPatientId(int patientId);
    boolean updatePaymentStatus(int invoiceId, String status);
}