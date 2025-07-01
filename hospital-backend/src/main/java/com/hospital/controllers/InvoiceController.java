package com.hospital.controllers;

import java.util.List;

import com.hospital.models.Invoice;
import com.hospital.services.InvoiceService;

import io.javalin.http.Context;

public class InvoiceController {

    private final InvoiceService invoiceService = new InvoiceService();

    public void getAllInvoices(Context ctx) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        ctx.json(invoices);
    }

    public void getInvoiceById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Invoice invoice = invoiceService.getInvoiceById(id);

        if (invoice != null) {
            ctx.json(invoice);
        } else {
            ctx.status(404).result("Invoice not found");
        }
    }

    public void createInvoice(Context ctx) {
        try {
            Invoice invoice = ctx.bodyAsClass(Invoice.class);
            boolean created = invoiceService.generateInvoice(invoice);

            if (created) {
                ctx.status(201).result("Invoice created successfully");
            } else {
                ctx.status(400).result("Failed to create invoice");
            }

        } catch (Exception e) {
            ctx.status(500).result("Invalid invoice data");
        }
    }

    public void updatePaymentStatus(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            String newStatus = ctx.queryParam("status");

            boolean updated = invoiceService.updatePaymentStatus(id, newStatus);

            if (updated) {
                ctx.status(200).result("Payment status updated");
            } else {
                ctx.status(400).result("Failed to update status");
            }

        } catch (Exception e) {
            ctx.status(500).result("Invalid request");
        }
    }
}
