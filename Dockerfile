FROM eclipse-temurin:17
WORKDIR /app
COPY ./target/vrs-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "vrs-0.0.1-SNAPSHOT.jar", "--server.port=5050"]