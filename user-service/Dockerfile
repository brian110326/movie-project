FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/user-service-1.0.jar UserService.jar
ENTRYPOINT ["java","-jar","UserService.jar"]