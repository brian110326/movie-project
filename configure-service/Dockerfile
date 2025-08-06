FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/configure-service-1.0.jar ConfigureServer.jar
ENTRYPOINT ["java","-jar","ConfigureServer.jar"]