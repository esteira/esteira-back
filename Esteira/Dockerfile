FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY Esteira/target/Esteira-0.0.1-SNAPSHOT.jar /app/esteira.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/esteira.jar"]