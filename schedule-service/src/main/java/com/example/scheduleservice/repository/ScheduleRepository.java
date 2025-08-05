package com.example.scheduleservice.repository;

import com.example.scheduleservice.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>,
        ScheduleRepositoryCustom {
    List<Schedule> findSchedulesByMovieId(Long movieId);

    List<Schedule> findSchedulesByTheaterId(Long theaterId);
}
