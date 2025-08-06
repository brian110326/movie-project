FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/seat-service-1.0.jar SeatService.jar
ENTRYPOINT ["java","-jar","SeatService.jar"]