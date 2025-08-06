FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/book-service-1.0.jar BookService.jar
ENTRYPOINT ["java","-jar","BookService.jar"]