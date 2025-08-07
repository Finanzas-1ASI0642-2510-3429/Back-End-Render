# --- ETAPA 1: CONSTRUCCIÓN (BUILD STAGE) ---
# Usa una imagen que tenga Maven y un JDK para compilar tu aplicación.
# Se ha cambiado 'maven:3.8.2-jdk-21' a 'maven:3.9.6-eclipse-temurin-21-alpine'
# que es un tag más común y disponible que incluye Maven con OpenJDK 21 en una imagen Alpine (ligera).
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

# Establece el directorio de trabajo dentro del contenedor para esta etapa
WORKDIR /app

# Copia solo el archivo pom.xml primero para aprovechar el cache de Docker.
# Si el pom.xml no cambia, Docker no volverá a descargar las dependencias.
COPY pom.xml .

# Descarga las dependencias de Maven. Esto ayuda a acelerar futuras construcciones.
RUN mvn dependency:go-offline -B

# Copia el resto de tu código fuente al directorio de trabajo
COPY src ./src

# Compila la aplicación y genera el archivo JAR ejecutable.
# '-DskipTests' es opcional y se usa para omitir la ejecución de los tests durante la construcción del Dockerfile.
# Si quieres ejecutar tests, omite '-DskipTests' o añádelos en una etapa de CI/CD separada.
RUN mvn clean package -DskipTests

# --- ETAPA 2: EJECUCIÓN (RUN STAGE) ---
# Usa una imagen base más ligera que solo contenga el JDK para ejecutar la aplicación.
# 'openjdk:21-jdk-slim' es ideal para producción porque es más pequeña.
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo para la ejecución de la aplicación
WORKDIR /app

# Copia el archivo JAR generado en la etapa 'build' (la primera etapa) a esta etapa de ejecución.
# 'app.jar' es un nombre común, asegúrate de que el JAR en 'target/' sea el correcto.
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto en el que tu aplicación Spring Boot escucha.
# Has especificado 8090, así que lo mantenemos aquí.
EXPOSE 8080

# Define el comando de entrada que se ejecutará cuando el contenedor se inicie.
# Esto inicia tu aplicación Spring Boot.
ENTRYPOINT ["java", "-jar", "app.jar"]
