-- 상영관 데이터
INSERT INTO theater (id, name, location, total_seats)
VALUES (1, 'room1', 'main', 20),
       (2, 'room2', 'main', 30),
       (3, 'special_room', 'main', 15);

-- 1관 좌석 (A1~A10, B1~B10)
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A1', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A2', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A3', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A4', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A5', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A6', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A7', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A8', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A9', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A10', 'NORMAL', 1);

INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B1', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B2', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B3', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B4', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B5', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B6', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B7', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B8', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B9', 'NORMAL', 1);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B10', 'NORMAL', 1);

-- 2관 좌석 (A1~A15, B1~B15)
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A1', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A2', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A3', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A4', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A5', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A6', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A7', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A8', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A9', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A10', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A11', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A12', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A13', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A14', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A15', 'NORMAL', 2);

INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B1', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B2', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B3', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B4', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B5', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B6', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B7', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B8', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B9', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B10', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B11', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B12', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B13', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B14', 'NORMAL', 2);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('B15', 'NORMAL', 2);

-- 특별관 좌석 (A1~A5)
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A1', 'VIP', 3);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A2', 'VIP', 3);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A3', 'VIP', 3);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A4', 'VIP', 3);
INSERT INTO seat (seat_number, seat_type, theater_id)
VALUES ('A5', 'VIP', 3);
