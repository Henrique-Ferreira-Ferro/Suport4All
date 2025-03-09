# Etapa de build usando Ubuntu
FROM ubuntu:latest AS build

# Atualiza os pacotes e instala o JDK e Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY . .

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# Cria os diretórios temporários para armazenar imagens
RUN mkdir -p /tmp/images /tmp/images-perfil

# Etapa final: Usa uma imagem menor para rodar a aplicação
FROM openjdk:17-jdk-slim

WORKDIR /app

# Expõe a porta 8080
EXPOSE 8080

# Copia apenas o JAR gerado na etapa de build
COPY --from=build /app/target/Suport4All.jar app.jar

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
