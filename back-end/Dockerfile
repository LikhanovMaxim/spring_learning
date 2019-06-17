# Use java 8 JDK from image
FROM openjdk:8-jdk-alpine
# We added a VOLUME pointing to "/tmp" because that is where a Spring Boot application creates working directories
# for Tomcat by default. The effect is to create a temporary file on your host under "/var/lib/docker"
# and link it to the container under "/tmp".
# This step is optional for the simple app that we wrote here, but can be necessary for other
# Spring Boot applications if they need to actually write in the filesystem
VOLUME /tmp
# It is variable:
ARG DEPENDENCY=target/dependency
# Copy the current directory contents into the container at /app/*
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# com.example.Application - entry class for spring
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.Application"]