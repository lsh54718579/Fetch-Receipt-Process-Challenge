# Step 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src /app/src

# Build the Spring Boot application (skip tests to speed up the process)
RUN mvn clean package -DskipTests

# Step 2: Create a smaller image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the builder image
COPY --from=builder /app/target/Fetch-Receipt-Process-Challenge-0.0.1-SNAPSHOT.jar fetch-receipt-process-service.jar

# Expose port 8080 for the application
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "fetch-receipt-process-service.jar"]
