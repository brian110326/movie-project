package com.example.movieservice.service;

import com.example.movieservice.dto.MovieDto;
import com.example.movieservice.entity.Movie;

import java.util.List;

public interface MovieService {

    MovieDto createMovie(MovieDto movieDto);
    List<Movie> getAllMovies(int page, int size);
    MovieDto getMovieById(Long id);

}
