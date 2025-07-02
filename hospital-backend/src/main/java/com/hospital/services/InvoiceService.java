package com.hospital.services;

import com.hospital.daos.InvoiceDAO;
import com.hospital.models.Invoice;

import java.util.List;

public class InvoiceService {

    public List<Invoice> getAllInvoices() {
        return InvoiceDAO.getAllInvoices();
    }

    public Invoice getInvoiceById(int id) {
        return InvoiceDAO.getInvoiceById(id);
    }

    public boolean generateInvoice(Invoice invoice) {
        return InvoiceDAO.createInvoice(invoice);
    }

    public boolean updatePaymentStatus(int invoiceId, String newStatus) {
        return InvoiceDAO.updatePaymentStatus(invoiceId, newStatus);
    }
}
