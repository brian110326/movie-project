package com.example.seatservice.dto;

import lombok.Data;

@Data
public class SeatStatusDto {

    private Long id;
    private Long scheduleId; // Schedule-Service
    private Long seatId; // Theater Service의 seat

    private boolean isReserved;

}
