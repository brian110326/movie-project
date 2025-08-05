package com.example.bookservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "theater-service")
public interface SeatTypeClient {

    @GetMapping("/theater-service/seats/checkType")
    List<String> checkSeatType(@RequestParam("ids") List<Long> ids);
}

