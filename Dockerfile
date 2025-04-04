# Usa una imagen de OpenJDK
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado por Maven
COPY target/transfer-api-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que corre la aplicación
EXPOSE 8082

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
