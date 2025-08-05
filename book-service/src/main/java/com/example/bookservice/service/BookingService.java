package com.example.bookservice.service;

import com.example.bookservice.dto.*;

import java.util.List;
import java.util.Map;

public interface BookingService {

    List<BookingDto> getAllBookings();
    List<BookingSeatDto> getBookedSeatsByBookingId(Long id);

    void processBooking(BookingRequestDto dto);
    void handleSeatAvailabilityResult(SeatAvailabilityChecked event);

    void handlePaymentCompleted(Map<String, Object> payload);

}
