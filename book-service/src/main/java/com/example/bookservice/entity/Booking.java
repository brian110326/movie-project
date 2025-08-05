package com.example.bookservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", length = 255)
    private String userId;
    private Long scheduleId;

    private LocalDateTime bookingTime;
    private Integer totalPrice;

}
