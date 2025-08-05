-- Booking 테이블 샘플 데이터 (3개)
INSERT INTO booking (id, user_id, schedule_id, booking_time, total_price)
VALUES (1, 101, 1001, '2025-07-30T14:00:00', 20000),
       (2, 102, 1002, '2025-07-30T15:00:00', 15000),
       (3, 103, 1003, '2025-07-30T16:00:00', 30000);

-- BookingSeat 테이블 샘플 데이터 (각 예약마다 1~2개 좌석)
INSERT INTO booking_seat (id, booking_id, seat_id)
VALUES (1, 1, 11),
       (2, 1, 12),
       (3, 2, 21),
       (4, 3, 31),
       (5, 3, 32),
       (6, 3, 33);
