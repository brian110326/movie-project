FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/book-service-1.0.jar BookService.jar
ENTRYPOINT ["java","-jar","BookService.jar"]