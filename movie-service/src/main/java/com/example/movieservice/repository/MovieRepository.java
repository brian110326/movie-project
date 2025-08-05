package com.example.movieservice.repository;

import com.example.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findMovieById(Long id);

}
