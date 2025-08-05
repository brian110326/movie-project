package com.example.theaterservice.controller;

import com.example.theaterservice.dto.SeatDto;
import com.example.theaterservice.service.SeatService;
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
@RequestMapping("/theater-service")
@Tag(name = "seat-controller", description = "상영관의 좌석을 위한 컨트롤러입니다")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{id}/seats")
    @Operation(summary = "전체 좌석 목록 조회 API",
            description = "전체 상영관의 좌석 목록을 조회하기 위한 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<List<SeatDto>> getSeats(@PathVariable Long id) {
        List<SeatDto> list = seatService.getSeatByTheaterId(id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/seats/checkType")
    @Operation(summary = "좌석 타입 조회 API",
            description = "특정 좌석들의 타입을 조회하기 위한 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public List<String> getSeatTypes(@RequestParam("ids") List<Long> ids) {
        return seatService.getSeatTypes(ids);
    }


}
