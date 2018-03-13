FROM openjdk:8-jdk-alpine
ADD . /EmailService
WORKDIR /EmailService
CMD ["java", "-jar", "target/caliber-email-service-0.0.1-SNAPSHOT.jar"]