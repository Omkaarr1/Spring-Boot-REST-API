# Use an official Maven image with OpenJDK 21 to build the application
FROM maven:3.8.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Use an official OpenJDK 21 image to run the application
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/rest-0.0.1-SNAPSHOT.jar /app/rest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/rest-0.0.1-SNAPSHOT.jar"]
