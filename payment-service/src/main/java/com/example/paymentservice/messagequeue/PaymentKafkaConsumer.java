package com.example.paymentservice.messagequeue;

import com.example.paymentservice.dto.BookingCreated;
import com.example.paymentservice.dto.PaymentCompleted;
import com.example.paymentservice.dto.PaymentRequested;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentKafkaConsumer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final PaymentService paymentService;

    @KafkaListener(topics = "booking.created", groupId = "payment-service")
    public void handleBookingCreated(Map<String, Object> payload) {
        ObjectMapper mapper = new ObjectMapper();
        BookingCreated event = mapper.convertValue(payload, BookingCreated.class);

        log.info("6단계 booking.created 토픽 수신 : {}", event);
        kafkaTemplate.send("payment.completed", new PaymentCompleted(event.getBookingId()));
        log.info("7단계 결제 요청 완료");
    }

    @KafkaListener(topics = "payment.requested")
    public void consumePaymentRequested(Map<String, Object> payload) {
        ObjectMapper mapper = new ObjectMapper();
        PaymentRequested event = mapper.convertValue(payload, PaymentRequested.class);
        log.info("6단계 payment.requested 수신 : {}", event);
        paymentService.processPayment(event);
    }

}
