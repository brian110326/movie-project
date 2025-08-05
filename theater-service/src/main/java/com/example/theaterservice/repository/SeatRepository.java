package com.example.theaterservice.repository;

import com.example.theaterservice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findSeatByTheaterId(Long theaterId);

    List<Seat> findSeatsByIdIn(Collection<Long> ids);
}
