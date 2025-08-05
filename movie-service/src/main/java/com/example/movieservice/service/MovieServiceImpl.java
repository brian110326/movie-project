package com.example.movieservice.service;

import com.example.movieservice.dto.MovieDto;
import com.example.movieservice.entity.Movie;
import com.example.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Movie movie = mapper.map(movieDto, Movie.class);
        movieRepository.save(movie);

        MovieDto dto = mapper.map(movie, MovieDto.class);
        return dto;
    }

    @Override
    @Cacheable(cacheNames = "getMovies",
            key = "'movie:page:' + #page + ':size:' + #size",
            cacheManager = "movieCacheManager")
    public List<Movie> getAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Movie> movies = movieRepository.findAll(pageable);

        return movies.getContent();
    }

    @Override
    @Cacheable(cacheNames = "getMovie", key = "'movie:' + #id",
            cacheManager = "movieCacheManager")
    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findMovieById(id);
        ModelMapper mapper = new ModelMapper();
        MovieDto dto = mapper.map(movie, MovieDto.class);

        return dto;
    }
}
