package com.example.seatservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Seat Service API specifications for MSA",
                description = "Seat Service API specifications with spring boot 3.5 + spring cloud",
                version = "v1.0.0")
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customOpenAPI() {
        String[] paths = {"/seat-service/**"};

        return GroupedOpenApi.builder()
                .group("좌석 정보 및 예매 여부에 대한 API")
                .pathsToMatch(paths)
                .build();
    }

}
