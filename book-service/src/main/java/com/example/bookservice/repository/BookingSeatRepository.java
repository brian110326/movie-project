package com.example.bookservice.repository;

import com.example.bookservice.entity.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {

    List<BookingSeat> findByBookingId(Long bookingId);

    List<BookingSeat> findAllByBookingId(Long id);
}
