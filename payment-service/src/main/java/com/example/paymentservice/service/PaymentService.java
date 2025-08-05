package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentRequested;
import com.example.paymentservice.entity.Payment;

public interface PaymentService {

    Payment getPaymentByBooking(Long bookingId);
    void processPayment(PaymentRequested event);
}
