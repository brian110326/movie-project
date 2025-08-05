package com.example.seatservice.controller;

import com.example.seatservice.dto.SeatStatusDto;
import com.example.seatservice.service.SeatStatusService;
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
@RequestMapping("/seat-service")
@Tag(name = "seat-controller", description = "좌석 정보를 위한 컨트롤러입니다")
public class SeatStatusController {

    private final SeatStatusService seatStatusService;

    @GetMapping("/seats/{id}")
    @Operation(summary = "전체 좌석 조회 API",
            description = "전체 좌석 조회를 위한 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<List<SeatStatusDto>> getList(@PathVariable Long id) {
        List<SeatStatusDto> list = seatStatusService.getSeatsBySchedule(id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/availability")
    @Operation(summary = "좌석 예매 여부 API",
            description = "특정 좌석의 예매 여부를 확인하기 위한 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<Boolean> checkAvailability(@RequestParam List<Long> seatIds, @RequestParam Long scheduleId) {
        boolean available = seatStatusService.checkAvailability(seatIds, scheduleId);
        return ResponseEntity.ok(available);
    }

}
