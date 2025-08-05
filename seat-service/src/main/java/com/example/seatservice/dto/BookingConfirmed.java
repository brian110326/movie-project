package com.example.seatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingConfirmed {
    private Long bookingId;
    private String userId;
    private Long scheduleId;
    private List<Long> seatIds;
    private Integer totalPrice;
}
