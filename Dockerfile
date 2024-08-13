FROM eclipse-temurin:17 as base
FROM base as vrs
WORKDIR /app
COPY ./target/vrs-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "vrs-0.0.1-SNAPSHOT.jar", "--server.port=5050"]