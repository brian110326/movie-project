package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentRequested;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment getPaymentByBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    @Override
    @Transactional
    public void processPayment(PaymentRequested event) {
        Payment payment = new Payment();
        payment.setBookingId(event.getBookingId());
        payment.setUserId(event.getUserId());
        payment.setAmount(event.getAmount());
        payment.setPaymentTime(LocalDateTime.now());
        paymentRepository.save(payment);
    }

}
