package com.example.theaterservice.dto;

import lombok.Data;

@Data
public class TheaterDto {

    private Long id;

    private String name;
    private String location;
    private Integer totalSeats;

}
