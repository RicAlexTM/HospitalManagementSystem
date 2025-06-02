package com.hms.services;

public interface IPaymentService {
    void processPayment(int invoiceId, double amount);
}