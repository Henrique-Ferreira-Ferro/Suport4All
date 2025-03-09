# Usa uma imagem com Maven e OpenJDK
FROM maven:3.8.6-openjdk-17 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY . .

# Compila o projeto (gera o JAR)
RUN mvn clean package -DskipTests

# Segunda etapa: imagem mais leve para rodar o JAR
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia apenas o JAR gerado na etapa anterior
COPY --from=build /app/target/Suport4All.jar app.jar

# Cria os diretórios temporários para armazenar imagens
RUN mkdir -p /tmp/images /tmp/images-perfil

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
