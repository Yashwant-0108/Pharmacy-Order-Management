FROM openjdk:17-jdk-slim

COPY target/PharmacyOrderManagement.jar app1.jar

CMD ["java", "-jar", "app1.jar"]