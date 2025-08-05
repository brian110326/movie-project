package com.example.bookservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "seat-service")
public interface SeatServiceClient {

    @GetMapping("/seat-service/availability")
    Boolean checkSeatAvailability(@RequestParam("seatIds") List<Long> seatIds,
                                  @RequestParam("scheduleId") Long scheduleId);
}
