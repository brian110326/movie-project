FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/movie-service-1.0.jar MovieService.jar
ENTRYPOINT ["java","-jar","MovieService.jar"]