package com.example.scheduleservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDto {

    private Long movieId;
    private Long theaterId;

    private LocalDateTime startTime;

}
