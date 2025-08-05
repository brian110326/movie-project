package com.example.movieservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseMovieDto {

    private Long id;
    private String title;
    private String genre;
    private Integer runningTime;
    private String descreption;
    private String director;
    private LocalDate releaseDate;

}
