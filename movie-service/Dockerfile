FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/movie-service-1.0.jar MovieService.jar
ENTRYPOINT ["java","-jar","MovieService.jar"]