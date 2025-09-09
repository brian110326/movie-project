FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/service-discovery-1.0.jar ServiceDiscovery.jar
ENTRYPOINT ["java","-jar","ServiceDiscovery.jar"]