FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/apigateway-service-1.0.jar ApiGateway.jar
ENTRYPOINT ["java","-jar","ApiGateway.jar"]