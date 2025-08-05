package com.example.seatservice.repository;

import com.example.seatservice.entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatStatusRepository extends JpaRepository<SeatStatus, Long> {

    List<SeatStatus> findAllByScheduleId(Long scheduleId);
    Optional<SeatStatus> findBySeatId(Long seatId);
    List<SeatStatus> findAllBySeatIdIn(List<Long> seatIds);

    List<SeatStatus> findByScheduleIdAndSeatIdInAndIsReservedTrue(Long scheduleId, List<Long> seatIds);
}
