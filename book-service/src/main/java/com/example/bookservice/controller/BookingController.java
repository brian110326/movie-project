package com.example.bookservice.controller;

import com.example.bookservice.dto.BookingDto;
import com.example.bookservice.dto.BookingRequestDto;
import com.example.bookservice.dto.BookingSeatDto;
import com.example.bookservice.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-service")
@Tag(name = "Booking API", description = "영화 예매 관련 API")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/bookings")
    @Operation(summary = "전체 예매 내역 조회 API", description = "모든 예매 정보를 조회하는 API (관리자 또는 테스트용)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "예매 정보 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<BookingDto>> getBookings() {
        List<BookingDto> list = bookingService.getAllBookings();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/reservedSeats/{id}")
    @Operation(summary = "예매된 좌석 조회 API", description = "예매 ID를 통해 예매된 좌석 목록을 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "좌석 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "예매 ID에 대한 좌석 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<BookingSeatDto>> getReservedSeats(@PathVariable Long id) {
        List<BookingSeatDto> list = bookingService.getBookedSeatsByBookingId(id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // jwt 적용되게 하기
    @PostMapping("/bookings")
    @Operation(summary = "예매 생성 API", description = "사용자가 영화 좌석을 예매하는 API (JWT 인증 필요)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "예매 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 또는 중복된 좌석"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDto requestDto) {
        try {
            bookingService.processBooking(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(requestDto);
        } catch (RuntimeException e) {
            Map<String, String> error = Map.of("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }


}
