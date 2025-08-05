package com.example.scheduleservice.repository;

import com.example.scheduleservice.entity.Schedule;

import java.util.List;

public interface ScheduleRepositoryCustom {
    List<Schedule> findTodayUpcomingSchedules();
}
