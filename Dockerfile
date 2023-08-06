FROM openjdk:17.0.2-jdk
WORKDIR /app
COPY target .
CMD java -jar school-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod