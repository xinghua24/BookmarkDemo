# Use the official maven/Java 11 image as builder
# https://hub.docker.com/_/maven
FROM maven:3.6.3-jdk-11-slim as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Use AdoptOpenJDK for base image.
# https://hub.docker.com/r/adoptopenjdk/openjdk11/
FROM adoptopenjdk:11-jre-hotspot

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/bookmark-*.jar /bookmark.jar

ENV PORT 8080

# Run the web service on container startup.
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","/bookmark.jar"]
