FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/seat-service-1.0.jar SeatService.jar
ENTRYPOINT ["java","-jar","SeatService.jar"]