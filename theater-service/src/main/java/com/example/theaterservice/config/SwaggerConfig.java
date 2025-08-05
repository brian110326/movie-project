package com.example.theaterservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Theater Service API specifications for MSA",
                description = "Theater Service API specifications with spring boot 3.5 + spring cloud",
                version = "v1.0.0")
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customOpenAPI() {
        String[] paths = {"/theater-service/**"};

        return GroupedOpenApi.builder()
                .group("영화 상영관과 각 상영관의 좌석에 대한 API")
                .pathsToMatch(paths)
                .build();
    }

}
