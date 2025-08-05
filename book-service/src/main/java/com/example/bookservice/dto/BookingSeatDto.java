package com.example.bookservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BookingSeatDto {

    private Long id;

    private Long bookingId;
    private Long seatId;

}
