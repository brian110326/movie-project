package com.example.theaterservice.service;

import com.example.theaterservice.dto.TheaterDto;
import com.example.theaterservice.entity.Theater;
import com.example.theaterservice.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository repository;

    @Override
    public TheaterDto getTheaterById(Long id) {
        Theater theater = repository.findTheaterById(id);
        return new ModelMapper().map(theater, TheaterDto.class);
    }

    @Override
    public List<TheaterDto> getAllTheaters() {
        List<Theater> theaters = repository.findAll();
        List<TheaterDto> dtos = new ArrayList<>();

        for (Theater theater : theaters) {
            dtos.add(new ModelMapper().map(theater, TheaterDto.class));
        }

        return dtos;
    }

}
