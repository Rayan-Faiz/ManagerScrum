FROM ubuntu:latest
LABEL authors="rayan"

ENTRYPOINT ["top", "-b"]

# Use a base image with Java and Maven pre-installed
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/managerscrum4iir-0.0.1-SNAPSHOT.jar /app/managerscrum4iir-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Environment variables for MySQL connection
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ManagerScrumX
ENV SPRING_DATASOURCE_USERNAME=rayan
ENV SPRING_DATASOURCE_PASSWORD=123

# Command to run the application
CMD ["java", "-jar", "managerscrum4iir-0.0.1-SNAPSHOT.jar"]
