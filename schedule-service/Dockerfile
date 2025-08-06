FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/schedule-service-1.0.jar ScheduleService.jar
ENTRYPOINT ["java","-jar","ScheduleService.jar"]