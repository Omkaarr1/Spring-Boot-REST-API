# Use an official Maven image with OpenJDK 17 to build the application
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

# Use an official OpenJDK image to run the application
FROM eclipse-temurin:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/rest-0.0.1-SNAPSHOT.jar /app/rest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/rest-0.0.1-SNAPSHOT.jar"]
