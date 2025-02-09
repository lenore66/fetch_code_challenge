# Step 1: Use an official OpenJDK runtime image
FROM openjdk:17-jdk-slim

# Step 2: Specify the working directory inside the container
WORKDIR /app

# Step 3: Copy the application's JAR file into the container
COPY build/libs/fetch_code_challenge-1.0-SNAPSHOT.jar  fetch_code_challenge-1.0-SNAPSHOT.jar

# Step 4: Specify the command to run the application
CMD ["java", "-jar", "app.jar"]