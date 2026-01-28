# Use Java 17 (Render supports it well)
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/*.jar app.jar

# Expose port (Render uses 10000 internally)
EXPOSE 10000

# Run Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
