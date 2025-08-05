package com.example.seatservice.service;

import com.example.seatservice.dto.SeatStatusDto;
import com.example.seatservice.entity.SeatStatus;

import java.util.List;

public interface SeatStatusService {

    List<SeatStatusDto> getSeatsBySchedule(Long id);
    boolean checkAvailability(List<Long> seatIds, Long scheduleId);
}
