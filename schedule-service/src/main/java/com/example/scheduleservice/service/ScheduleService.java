package com.example.scheduleservice.service;

import com.example.scheduleservice.dto.ScheduleDto;
import com.example.scheduleservice.dto.ScheduleViewDto;

import java.util.List;

public interface ScheduleService {

    ScheduleViewDto createSchedule(ScheduleDto scheduleDto);
    List<ScheduleViewDto> getSchedulesByMovieId(Long id);
    List<ScheduleViewDto> getSchedulesByTheaterId(Long id);

    List<ScheduleViewDto> getSchedulesToday();

}
