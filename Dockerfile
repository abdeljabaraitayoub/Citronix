FROM openjdk:17

WORKDIR /app

COPY ./target/Citronix-0.0.1-SNAPSHOT.jar .


CMD ["java", "-jar", "Citronix-0.0.1-SNAPSHOT.jar"]
