package com.example.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreated {

    private Long bookingId;
    private String userId;
    private List<Long> seatIds;
    private Integer totalPrice;

}
