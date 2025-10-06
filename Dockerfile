# Dockerfile.dev

# Use a imagem Maven + JDK 17 (para build e testes)
FROM maven:3.9.2-eclipse-temurin-17 AS dev

WORKDIR /app

# Copia o pom e o código-fonte
COPY pom.xml .
COPY src ./src

# Instala dependências sem rodar os testes ainda
RUN mvn dependency:resolve

# Comando default para abrir o container pronto para desenvolvimento/testes
CMD ["bash"]
