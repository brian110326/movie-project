package com.example.theaterservice.controller;

import com.example.theaterservice.dto.TheaterDto;
import com.example.theaterservice.service.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/theater-service")
@Tag(name = "theater-controller", description = "상영관을 위한 컨트롤러입니다")
public class TheaterController {

    private final TheaterService theaterService;

    @GetMapping("/theaters")
    @Operation(summary = "전체 상영관 목록 조회 API",
            description = "전체 상영관을 조회하기 위한 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<List<TheaterDto>> getAll() {
        List<TheaterDto> list = theaterService.getAllTheaters();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/theaters/{id}")
    @Operation(summary = "특정 상영관 목록 조회 API",
            description = "특정 상영관을 조회하기 위한 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<TheaterDto> getTheater(@PathVariable Long id) {
        TheaterDto dto = theaterService.getTheaterById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

}
