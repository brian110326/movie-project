package com.example.scheduleservice.service;

import com.example.scheduleservice.client.MovieServiceClient;
import com.example.scheduleservice.dto.MovieDto;
import com.example.scheduleservice.dto.ScheduleDto;
import com.example.scheduleservice.dto.ScheduleViewDto;
import com.example.scheduleservice.entity.Schedule;
import com.example.scheduleservice.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;
    private final MovieServiceClient movieServiceClient;

    @Override
    public ScheduleViewDto createSchedule(ScheduleDto scheduleDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Schedule schedule = mapper.map(scheduleDto, Schedule.class);
        //schedule.setEndTime(schedule.getStartTime() + 영화 상영시간만큼);
        MovieDto movie = movieServiceClient.getMovie(schedule.getMovieId());
        schedule.setEndTime(schedule.getStartTime().plusMinutes(movie.getRunningTime()));
        repository.save(schedule);

        ScheduleViewDto dto = mapper.map(schedule, ScheduleViewDto.class);
        return dto;
    }

    @Override
    /*@Cacheable(cacheNames = "getSchedules1", key = "'schedule:all:byMovieId'",
            cacheManager = "scheduleCacheManager")*/
    public List<ScheduleViewDto> getSchedulesByMovieId(Long id) {
        List<Schedule> list = repository.findSchedulesByMovieId(id);
        List<ScheduleViewDto> dtos = new ArrayList<>();
        for (Schedule schedule : list) {
            dtos.add(new ModelMapper().map(schedule, ScheduleViewDto.class));
        }
        return dtos;
    }

    @Override
    /*@Cacheable(cacheNames = "getSchedules2", key = "'schedule:all:byTheaterId'",
            cacheManager = "scheduleCacheManager")*/
    public List<ScheduleViewDto> getSchedulesByTheaterId(Long id) {
        List<Schedule> list = repository.findSchedulesByTheaterId(id);
        List<ScheduleViewDto> dtos = new ArrayList<>();
        for (Schedule schedule : list) {
            dtos.add(new ModelMapper().map(schedule, ScheduleViewDto.class));
        }
        return dtos;
    }

    @Override
    public List<ScheduleViewDto> getSchedulesToday() {
        List<Schedule> list = repository.findTodayUpcomingSchedules();
        List<ScheduleViewDto> dtos = new ArrayList<>();
        for (Schedule schedule : list) {
            dtos.add(new ModelMapper().map(schedule, ScheduleViewDto.class));
        }
        return dtos;
    }

}
