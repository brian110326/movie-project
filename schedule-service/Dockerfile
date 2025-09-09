FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/schedule-service-1.0.jar ScheduleService.jar
ENTRYPOINT ["java","-jar","ScheduleService.jar"]