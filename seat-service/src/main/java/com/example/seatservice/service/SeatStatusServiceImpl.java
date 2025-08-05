package com.example.seatservice.service;

import com.example.seatservice.dto.SeatStatusDto;
import com.example.seatservice.entity.SeatStatus;
import com.example.seatservice.repository.SeatStatusRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatStatusServiceImpl implements SeatStatusService {

    private final SeatStatusRepository repository;

    @Override
    public List<SeatStatusDto> getSeatsBySchedule(Long id) {
        List<SeatStatus> list = repository.findAllByScheduleId(id);
        List<SeatStatusDto> dtos = new ArrayList<>();

        for (SeatStatus seatStatus : list) {
            dtos.add(new ModelMapper().map(seatStatus, SeatStatusDto.class));
        }

        return dtos;
    }

    @Override
    public boolean checkAvailability(List<Long> seatIds, Long scheduleId) {
        List<SeatStatus> reservedSeats = repository.findByScheduleIdAndSeatIdInAndIsReservedTrue(scheduleId, seatIds);

        return reservedSeats.isEmpty();
    }

}
