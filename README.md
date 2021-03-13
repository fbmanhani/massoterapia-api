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

A aplicação utiliza-se, também, de uma base de dados Firebase e esta deve ser criada e configurada para que a aplicação funcione corretamente.

Entre no link https://console.firebase.google.com/ e crie a sua realtime database e o client para a sua aplicação. Após tudo criado, siga os passos abaixo:
- Inclua a URL da base de dados na seguinte propriedade do application.yml
    ```
    firebase-realtime-database.database-url: https://databasexxx.firebaseio.com/
    ```
    
- Crie o arquivo firebase-web-api-key.txt em src/main/resources e inclua nele o clientId do Firebase da seguinte forma:
    ```
    firebase-web-api-key : example_client_id
    ```
- Configure nas variáveis de ambiente do sistema o usuário e senha do usuário de serviço do Firebase
    ```
    FIREBASE_USERNAME : seu_username
    FIREBASE_PASSWORD : seu_password
    ```

### LDAP

A aplicação faz uso do Spring LDAP para se comunicar com uma base de usuários local. Essa base está disponível no arquivo local-ldap.ldif.
