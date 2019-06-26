# Use java 8 JDK from image
# You can also save about 20MB in the base image by using the "jre" label instead of "jdk". Not all apps work with a JRE (as opposed to a JDK), but most do,
# https://spring.io/guides/topicals/spring-boot-docker/
FROM openjdk:8-jdk-alpine
# We added a VOLUME pointing to "/tmp" because that is where a Spring Boot application creates working directories
# for Tomcat by default. The effect is to create a temporary file on your host under "/var/lib/docker"
# and link it to the container under "/tmp".
# This step is optional for the simple app that we wrote here, but can be necessary for other
# Spring Boot applications if they need to actually write in the filesystem
VOLUME ~/spring:/var/log/spring
# It is variable:
ARG DEPENDENCY=target/dependency
# Copy the current directory contents into the container at /app/*
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
# There are now 3 layers, with all the application resources in the later 2 layers.
# If the application dependencies don’t change, then the first layer (from BOOT-INF/lib) will not change,
# so the build will be faster, and so will the startup of the container at runtime as long as the base layers
# are already cached.
# https://spring.io/guides/topicals/spring-boot-docker/
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# com.example.Application - entry class for spring
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.Application"]