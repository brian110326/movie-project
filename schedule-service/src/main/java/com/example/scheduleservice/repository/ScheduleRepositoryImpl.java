package com.example.scheduleservice.repository;

import com.example.scheduleservice.entity.QSchedule;
import com.example.scheduleservice.entity.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public ScheduleRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public List<Schedule> findTodayUpcomingSchedules() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endofDay = now.toLocalDate().atTime(23, 59, 59);

        QSchedule schedule = QSchedule.schedule;

        return jpaQueryFactory.selectFrom(schedule)
                .where(schedule.startTime.between(now, endofDay))
                .fetch();
    }
}
