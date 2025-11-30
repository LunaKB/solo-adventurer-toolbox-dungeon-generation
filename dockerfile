# Step 1: Use an official base image from Docker Hub
FROM eclipse-temurin:21

# Step 2: Set the working directory inside the container
RUN mkdir /app
WORKDIR /app

# Step 3: Copy the Spring Boot JAR file into the container
COPY build/libs/*.jar app.jar

# Step 4: Expose the port your application runs on
EXPOSE 8102

# Step 5: Define the command to run your Spring Boot application
CMD ["java", "-jar", "/app/app.jar"]
