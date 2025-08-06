FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/payment-service-1.0.jar PaymentService.jar
ENTRYPOINT ["java","-jar","PaymentService.jar"]