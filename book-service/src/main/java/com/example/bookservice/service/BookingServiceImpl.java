package com.example.bookservice.service;

import com.example.bookservice.client.SeatServiceClient;
import com.example.bookservice.client.SeatTypeClient;
import com.example.bookservice.dto.*;
import com.example.bookservice.entity.Booking;
import com.example.bookservice.entity.BookingSeat;
import com.example.bookservice.messagequeue.KafkaProducer;
import com.example.bookservice.repository.BookingRepository;
import com.example.bookservice.repository.BookingSeatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final KafkaProducer kafkaProducer;
    private final SeatServiceClient seatServiceClient;
    private final SeatTypeClient seatTypeClient;

    @Override
    public List<BookingDto> getAllBookings() {
        List<Booking> list = bookingRepository.findAll();
        List<BookingDto> dtos = new ArrayList<>();
        for (Booking booking : list) {
            dtos.add(new ModelMapper().map(booking, BookingDto.class));
        }

        return dtos;
    }

    @Override
    public List<BookingSeatDto> getBookedSeatsByBookingId(Long id) {
        List<BookingSeat> list = bookingSeatRepository.findByBookingId(id);
        List<BookingSeatDto> dtos = new ArrayList<>();
        for (BookingSeat booking : list) {
            dtos.add(new ModelMapper().map(booking, BookingSeatDto.class));
        }

        return dtos;
    }

    @Override
    public void processBooking(BookingRequestDto dto) {
        CheckSeatAvailabilityRequested event = new CheckSeatAvailabilityRequested(
                dto.getScheduleId(), dto.getSeatIds(), dto.getUserId()
        );

        boolean available = seatServiceClient.checkSeatAvailability(dto.getSeatIds(), dto.getScheduleId());
        if (!available) {
            throw new RuntimeException("이미 예약된 좌석입니다.");
        }

        kafkaProducer.send("booking.seat.check-requested", event);
        log.info("1단계 booking.seat.check-requested 토픽 전송 : " + dto);
    }

    private Integer calculateTotalPrice(List<Long> seatIds) {
        Integer total = 0;
        List<String> list = seatTypeClient.checkSeatType(seatIds);
        for (String s : list) {
            if (s.equals("NORMAL")) {
                total += 12000;
            }
            else{
                total += 15000;
            }
        }
        return total;
    }

    @Override
    public void handleSeatAvailabilityResult(SeatAvailabilityChecked event) {
        if (event.isAvailable()) {
            Booking booking = new Booking();
            booking.setUserId(event.getUserId());
            booking.setScheduleId(event.getScheduleId());
            booking.setBookingTime(LocalDateTime.now());
            booking.setTotalPrice(calculateTotalPrice(event.getSeatIds()));

            bookingRepository.save(booking);

            for (Long seatId : event.getSeatIds()) {
                BookingSeat bookingSeat = new BookingSeat();
                bookingSeat.setBookingId(booking.getId());
                bookingSeat.setSeatId(seatId);
                bookingSeatRepository.save(bookingSeat);
            }

            BookingCreated bookingCreated = new BookingCreated(
                    booking.getId(),
                    booking.getUserId(),
                    event.getSeatIds(),
                    booking.getTotalPrice()
            );
            kafkaProducer.send("booking.created", bookingCreated);
            log.info("5단계 booking.created 토픽 전송 : {}", bookingCreated);

            PaymentRequested paymentRequested = new PaymentRequested(
                    booking.getId(),
                    booking.getUserId(),
                    booking.getTotalPrice()
            );
            kafkaProducer.send("payment.requested", paymentRequested);

        } else {
            throw new RuntimeException("이 좌석은 이미 예매된 좌석입니다.");
        }
    }

    @Override
    public void handlePaymentCompleted(Map<String, Object> payload) {
        ObjectMapper mapper = new ObjectMapper();
        PaymentCompleted event = mapper.convertValue(payload, PaymentCompleted.class);

        Booking booking = bookingRepository.findById(event.getBookingId()).orElseThrow();
        List<Long> seatIds = bookingSeatRepository.findAllByBookingId(booking.getId())
                .stream().map(BookingSeat::getSeatId).collect(Collectors.toList());

        kafkaProducer.send("booking.confirmed", new BookingConfirmed(event.getBookingId(),
                seatIds));
    }

}
