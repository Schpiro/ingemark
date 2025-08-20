# Stage 1: Build the JAR with Gradle
FROM gradle:8.10.2-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test --no-daemon

# Stage 2: Run the JAR
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]