package com.example.theaterservice.service;

import com.example.theaterservice.dto.SeatDto;

import java.util.List;

public interface SeatService {

    List<SeatDto> getSeatByTheaterId(Long id);
    List<String> getSeatTypes(List<Long> ids);

}
