# java 17 como el entorno dev
FROM openjdk:17-jdk-slim

# directorio de trabajo
WORKDIR /app

# el jar previamente compilado lo pasamos
COPY target/fullstack-0.0.1-SNAPSHOT.jar app.jar
# por conveniencia uso este puerto
EXPOSE 9191

# se ejecuta la aplicaicon si se construye
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
