package com.example.theaterservice.dto;

import lombok.Data;

@Data
public class SeatDto {

    private Long id;

    private String seatNumber;
    private String seatType;

    private Long theaterId;

}
