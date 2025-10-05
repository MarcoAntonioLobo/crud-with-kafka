# Etapa de build
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/user-crud-events-0.0.1-SNAPSHOT.jar app.jar

# Porta do serviço
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
