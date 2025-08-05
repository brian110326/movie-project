package com.example.scheduleservice.controller;

import com.example.scheduleservice.dto.ScheduleDto;
import com.example.scheduleservice.dto.ScheduleViewDto;
import com.example.scheduleservice.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule-service")
@Tag(name = "Schedule API", description = "영화 상영 스케줄 관련 API")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/createSchedule")
    @Operation(summary = "스케줄 생성 API", description = "영화 상영 스케줄을 생성하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "스케줄 생성 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ScheduleViewDto> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        ScheduleViewDto schedule = scheduleService.createSchedule(scheduleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
    }

    @GetMapping("/movie/{id}")
    @Operation(summary = "영화별 스케줄 조회 API", description = "특정 영화의 상영 스케줄을 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "스케줄 조회 성공"),
            @ApiResponse(responseCode = "404", description = "영화 ID에 해당하는 스케줄 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<ScheduleViewDto>> getScheduleByMovie(@PathVariable Long id) {
        List<ScheduleViewDto> schedules = scheduleService.getSchedulesByMovieId(id);
        return ResponseEntity.status(HttpStatus.OK).body(schedules);
    }

    @GetMapping("/theater/{id}")
    @Operation(summary = "극장별 스케줄 조회 API", description = "특정 극장의 상영 스케줄을 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "스케줄 조회 성공"),
            @ApiResponse(responseCode = "404", description = "극장 ID에 해당하는 스케줄 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<ScheduleViewDto>> getScheduleByTheater(@PathVariable Long id) {
        List<ScheduleViewDto> schedules = scheduleService.getSchedulesByTheaterId(id);
        return ResponseEntity.status(HttpStatus.OK).body(schedules);
    }

    @GetMapping("/today")
    @Operation(summary = "오늘의 스케줄 조회 API", description = "오늘 날짜에 해당하는 모든 영화 상영 스케줄을 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "스케줄 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<ScheduleViewDto>> getScheduleToday() {
        List<ScheduleViewDto> schedules = scheduleService.getSchedulesToday();
        return ResponseEntity.status(HttpStatus.OK).body(schedules);
    }

}
