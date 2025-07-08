package com.hospital.controllers;

import java.util.List;

import com.hospital.daos.InvoiceDAO;
import com.hospital.daos.impl.InvoiceDAOImpl;
import com.hospital.models.Invoice;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class InvoiceController {
    private final InvoiceDAO invoiceDAO;

    public InvoiceController() {
        this.invoiceDAO = new InvoiceDAOImpl();
    }

    public void setupRoutes(Javalin app) {
        // Get all invoices
        app.get("/invoices", this::getAllInvoices);
        
        // Get invoice by ID
        app.get("/invoices/{id}", this::getInvoiceById);
        
        // Create new invoice
        app.post("/invoices", this::createInvoice);
        
        // Update invoice
        app.put("/invoices/{id}", this::updateInvoice);
        
        // Delete invoice
        app.delete("/invoices/{id}", this::deleteInvoice);
        
        // Get invoices by patient
        app.get("/patients/{patientId}/invoices", this::getInvoicesByPatient);
        
        // Update payment status
        app.patch("/invoices/{id}/status", this::updatePaymentStatus);
    }

    private void getAllInvoices(Context ctx) {
        List<Invoice> invoices = invoiceDAO.findAll();
        ctx.json(invoices).status(200);
    }

    private void getInvoiceById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        invoiceDAO.findById(id).ifPresentOrElse(
            invoice -> ctx.json(invoice).status(200),
            () -> ctx.status(404).result("Invoice not found")
        );
    }

    private void createInvoice(Context ctx) {
        Invoice invoice = ctx.bodyAsClass(Invoice.class);
        if (invoiceDAO.create(invoice)) {
            ctx.status(201).json(invoice);
        } else {
            ctx.status(400).result("Failed to create invoice");
        }
    }

    private void updateInvoice(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Invoice updatedInvoice = ctx.bodyAsClass(Invoice.class);
        updatedInvoice.setId(id);
        
        if (invoiceDAO.update(updatedInvoice)) {
            ctx.status(200).json(updatedInvoice);
        } else {
            ctx.status(400).result("Failed to update invoice");
        }
    }

    private void deleteInvoice(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (invoiceDAO.delete(id)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Invoice not found");
        }
    }

    private void getInvoicesByPatient(Context ctx) {
        int patientId = Integer.parseInt(ctx.pathParam("patientId"));
        List<Invoice> invoices = invoiceDAO.findByPatientId(patientId);
        ctx.json(invoices).status(200);
    }

    private void updatePaymentStatus(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String status = ctx.body();
        
        if (invoiceDAO.updatePaymentStatus(id, status)) {
            ctx.status(200).result("Payment status updated");
        } else {
            ctx.status(400).result("Failed to update payment status");
        }
    }
}