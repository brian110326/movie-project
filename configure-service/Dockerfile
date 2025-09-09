FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/configure-service-1.0.jar ConfigureServer.jar
ENTRYPOINT ["java","-jar","ConfigureServer.jar"]