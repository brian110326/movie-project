package com.example.theaterservice.service;

import com.example.theaterservice.dto.SeatDto;
import com.example.theaterservice.entity.Seat;
import com.example.theaterservice.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository repository;

    @Override
    public List<SeatDto> getSeatByTheaterId(Long id) {
        List<Seat> list = repository.findSeatByTheaterId(id);
        List<SeatDto> dtos = new ArrayList<>();

        for (Seat seat : list) {
            dtos.add(new ModelMapper().map(seat, SeatDto.class));
        }

        return dtos;
    }

    @Override
    public List<String> getSeatTypes(List<Long> ids) {
        List<Seat> list = repository.findSeatsByIdIn(ids);
        List<String> types = new ArrayList<>();
        for (Seat seat : list) {
            types.add(seat.getSeatType());
        }

        return types;
    }

}
