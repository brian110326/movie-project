FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/theater-service-1.0.jar TheaterService.jar
ENTRYPOINT ["java","-jar","TheaterService.jar"]