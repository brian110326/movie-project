package com.example.bookservice.messagequeue;

import com.example.bookservice.dto.PaymentCompleted;
import com.example.bookservice.dto.SeatAvailabilityChecked;
import com.example.bookservice.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingKafkaConsumer {

    private final BookingService bookingService;

    @KafkaListener(topics = "booking.seat.checked", groupId = "booking-service")
    public void consumeSeatChecked(Map<String, Object> payload) {
        ObjectMapper mapper = new ObjectMapper();
        SeatAvailabilityChecked event = mapper.convertValue(payload, SeatAvailabilityChecked.class);

        log.info("4단계 booking.seat.checked 토픽 수신 : {}", event);
        bookingService.handleSeatAvailabilityResult(event);
    }

    @KafkaListener(topics = "payment.completed", groupId = "booking-service")
    public void consumePaymentCompleted(Map<String, Object> payload) {
        bookingService.handlePaymentCompleted(payload);
    }


}
