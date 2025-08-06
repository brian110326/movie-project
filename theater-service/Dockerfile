FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/theater-service-1.0.jar TheaterService.jar
ENTRYPOINT ["java","-jar","TheaterService.jar"]