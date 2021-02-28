# API
Pré-requisitos:
- JDK 11
- Docker
- IDE de sua preferência
- Criação do client no Firebase https://console.firebase.google.com/

## Como executar

- Importar o projeto na IDE (Opcional)
- Construir o projeto 

    ```
    ./mvnw clean package
    ```
- Subir o mysql via compose

    ```
    docker-compose -f docker/docker-compose.yml
    ```
- Inicie a aplicação pela IDE executando a classe MassoterapiaApiApplication.java como um projeto spring boot ou então por linha de comando

    ```
    ./mvnw spring-boot:run
    ```
    
### Base de dados

Ao executar a aplicação, o Spring Boot executará o script do Flyway para a criação da base de dados MySQL.

A aplicação utiliza-se, também, de uma base de dados Firebase e esta deve ser configurada no application.yml para que a aplicação funcione corretamente.

