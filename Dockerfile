# Use an official Maven image to build the application
FROM maven:3.8.7-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/rest-0.0.1-SNAPSHOT.jar /app/rest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/rest-0.0.1-SNAPSHOT.jar"]
