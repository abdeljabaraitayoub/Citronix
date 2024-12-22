# Use OpenJDK 11 runtime image for the final image
FROM openjdk:11-jre-slim

# Set the working directory for the application
WORKDIR /app

# Copy the pre-built JAR file from the local machine into the container
COPY ./target/Citronix-0.0.1-SNAPSHOT.jar .

# Command to run the JAR file
CMD ["java", "-jar", "Citronix-0.0.1-SNAPSHOT.jar"]
