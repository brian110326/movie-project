package com.example.movieservice.controller;

import com.example.movieservice.dto.MovieDto;
import com.example.movieservice.entity.Movie;
import com.example.movieservice.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie-service")
@Tag(name = "Movie API", description = "영화 등록 및 조회 관련 API")
public class MovieController {

    private final MovieService movieService;
    private final Environment env;

    @GetMapping("/health-check")
    @Operation(summary = "헬스 체크 API", description = "Movie Service가 실행 중인지 확인하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "헬스 체크 성공")
    })
    public String status() {
        return String.format("It's Working in Order Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }

    @PostMapping("/movies")
    @Operation(summary = "영화 등록 API", description = "관리자가 새로운 영화를 등록하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "영화 등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        MovieDto dto = movieService.createMovie(movieDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/movies/{id}")
    @Operation(summary = "단일 영화 조회 API", description = "영화 ID를 통해 해당 영화 정보를 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "영화 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 영화 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<MovieDto> getSingleMovie(@PathVariable Long id) {
        MovieDto dto = movieService.getMovieById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/movies")
    @Operation(summary = "영화 목록 조회 API", description = "페이징 정보를 기반으로 전체 영화 목록을 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "영화 목록 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        List<Movie> allMovies = movieService.getAllMovies(page, size);
        /*List<MovieDto> dtos = new ArrayList<>();
        for (Movie movie : allMovies) {
            dtos.add(new ModelMapper().map(movie, MovieDto.class));
        }*/

        return ResponseEntity.status(HttpStatus.OK).body(allMovies);
    }

}
