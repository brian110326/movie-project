package com.example.userservice.security;

import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.http.client.HttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final Environment env;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http, HttpClientProperties httpClientProperties) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder
                = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http.csrf(csrf -> csrf.disable());
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        // 세션을 사용하지 않겠다
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/health-check/**").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/welcome/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/user-service/**").permitAll()
                        .requestMatchers("/users").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/**").access(
                                new WebExpressionAuthorizationManager(
                                        "hasIpAddress('127.0.0.1') or hasIpAddress('::1') or " +
                                                "hasIpAddress('172.31.96.1') or hasIpAddress('::1') or " +
                                                "hasIpAddress('172.30.1.30') or hasIpAddress('::1') or " +
                                                "hasIpAddress('43.200.169.229')"
                                )
                        ));


        http.authenticationManager(authenticationManager);
        http.addFilter(getAuthenticationFilter(authenticationManager));
        http.headers(headers -> headers.frameOptions(
                frameOptions -> frameOptions.sameOrigin()
        ));

        return http.build();
    }

    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, userService, env);
        authenticationFilter.setAuthenticationManager(authenticationManager);

        return authenticationFilter;
    }

}
