FROM maven:3.8.6-openjdk-11-slim AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=build /app/target/Citronix-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "Citronix-0.0.1-SNAPSHOT.jar"]
