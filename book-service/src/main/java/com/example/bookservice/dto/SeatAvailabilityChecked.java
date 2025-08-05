package com.example.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatAvailabilityChecked {

    private boolean available;
    private Long scheduleId;
    private List<Long> seatIds;
    private String userId;

}
