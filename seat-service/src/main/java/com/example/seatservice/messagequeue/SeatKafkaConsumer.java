package com.example.seatservice.messagequeue;

import com.example.seatservice.dto.BookingConfirmed;
import com.example.seatservice.dto.CheckSeatAvailabilityRequested;
import com.example.seatservice.dto.SeatAvailabilityChecked;
import com.example.seatservice.entity.SeatStatus;
import com.example.seatservice.repository.SeatStatusRepository;
import com.example.seatservice.service.SeatStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeatKafkaConsumer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final SeatStatusRepository seatStatusRepository;

    @KafkaListener(topics = "booking.seat.check-requested", groupId = "seat-service")
    public void checkSeatAvailability(Map<String, Object> payload) {
        ObjectMapper mapper = new ObjectMapper();
        CheckSeatAvailabilityRequested event = mapper.convertValue(payload, CheckSeatAvailabilityRequested.class);

        log.info("2단계 booking.seat.check-requested 토픽 수신 : {}", event);
        List<SeatStatus> seats = seatStatusRepository.findAllById(event.getSeatIds());
        boolean available = seats.stream().allMatch(seatStatus -> !seatStatus.isReserved());

        SeatAvailabilityChecked result = new SeatAvailabilityChecked(
                available, event.getScheduleId(), event.getSeatIds(),
                event.getUserId()
        );

        kafkaTemplate.send("booking.seat.checked", result);
        log.info("3단계 booking.seat.checked 토픽 전송 : {}", result);
    }

    @KafkaListener(topics = "booking.confirmed", groupId = "seat-service")
    public void confirmSeat(Map<String, Object> payload) {
        ObjectMapper mapper = new ObjectMapper();
        BookingConfirmed event = mapper.convertValue(payload, BookingConfirmed.class);

        // 좌석 예약 상태 isReserved = true 로 변경
        List<Long> seatIds = event.getSeatIds();
        for (Long seatId : seatIds) {
            System.out.println("seatId = " + seatId);
            Optional<SeatStatus> optionalSeatStatus = seatStatusRepository.findBySeatId(seatId);
            SeatStatus seatStatus = optionalSeatStatus.orElseThrow(
                    () -> new RuntimeException("SeatStatus not Found for Id : " + seatId)
            );
            seatStatus.setReserved(true);
            seatStatusRepository.save(seatStatus);
        }
    }

}
