package com.example.bookservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDto {

    private Long movieId;
    private Long scheduleId;
    private List<Long> seatIds;
    private String userId;

}
