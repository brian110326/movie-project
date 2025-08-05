package com.example.theaterservice.repository;

import com.example.theaterservice.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    Theater findTheaterById(Long id);
}
