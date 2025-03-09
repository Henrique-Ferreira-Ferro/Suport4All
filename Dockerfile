# Usa uma imagem leve do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado do projeto para dentro do container
COPY target/Suport4All.jar app.jar

# Cria os diretórios temporários para armazenar imagens (Render os apaga ao reiniciar)
RUN mkdir -p /tmp/images /tmp/images-perfil

# Expõe a porta 8080 (Render usa isso para rodar a aplicação)
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot
CMD ["java", "-jar", "app.jar"]