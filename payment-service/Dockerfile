FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/payment-service-1.0.jar PaymentService.jar
ENTRYPOINT ["java","-jar","PaymentService.jar"]