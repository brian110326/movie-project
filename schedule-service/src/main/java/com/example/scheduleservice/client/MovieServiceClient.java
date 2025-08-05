package com.example.scheduleservice.client;

import com.example.scheduleservice.dto.MovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service")
public interface MovieServiceClient {

    @GetMapping("/movie-service/movies/{id}")
    MovieDto getMovie(@PathVariable Long id);

}
