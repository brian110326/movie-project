package com.example.paymentservice.controller;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-service")
@Tag(name = "Payment API", description = "결제 관련 API")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/booking/{bookingId}")
    @Operation(summary = "결제 정보 조회 API", description = "예약 ID로 결제 정보를 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "결제 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 예약 ID에 대한 결제 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public Payment getPayment(@PathVariable Long bookingId) {
        return paymentService.getPaymentByBooking(bookingId);
    }

}
