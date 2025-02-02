# Use the official OpenJDK image from the Docker Hub
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory
COPY target/Fetch-Receipt-Process-Challenge-0.0.1-SNAPSHOT.jar fetch-receipt-process-service.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "fetch-receipt-process-service.jar"]
