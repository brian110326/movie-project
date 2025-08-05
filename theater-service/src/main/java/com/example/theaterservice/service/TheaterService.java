package com.example.theaterservice.service;

import com.example.theaterservice.dto.TheaterDto;

import java.util.List;

public interface TheaterService {

    TheaterDto getTheaterById(Long id);
    List<TheaterDto> getAllTheaters();

}
