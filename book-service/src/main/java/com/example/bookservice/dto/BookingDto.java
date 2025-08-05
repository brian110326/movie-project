package com.example.bookservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDto {
    private Long id;

    private Long userId;
    private Long scheduleId;

    private LocalDateTime bookingTime;
    private Integer totalPrice;

}
