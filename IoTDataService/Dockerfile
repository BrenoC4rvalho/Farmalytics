# Estágio 1: Build - Usa uma imagem Maven para compilar o projeto
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY . .
RUN mvn clean install -DskipTests

# Estágio 2: Run - Usa uma imagem Java leve para rodar o JAR compilado
FROM openjdk:21-slim
WORKDIR /app
# Copia o JAR do estágio de build para o estágio de execução
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]