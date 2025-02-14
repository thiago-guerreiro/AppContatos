# AppContatos

API REST para gerenciar um sistema de cadastro de pessoas e seus respectivos contatos, onde cada pessoa pode ter vários contatos, e as operações CRUD (Criar, Ler, Atualizar, Deletar) podem ser realizadas tanto para pessoas quanto para contatos.

## Endpoints

### Pessoa 
- **POST /api/pessoas**  -  Cria uma nova pessoa
- **GET /api/pessoas/{id}**  -  Retorna os dados de uma essoa por ID
- **GET /api/pessoas/maladireta/{id}**  -  Retorna os dados de uma essoa por ID para mala direta
- **GET /api/pessoas**  -  Lista todas as essoas
- **PUT /api/pessoas/{id}**  -  Atualiza uma essoa existente
- **DELETE /api/pessoas/{id}**  -  Remove uma Pessoa por ID. 

### Contato
- **POST /api/contatos/**  -  Adiciona um novo contato a uma pessoa
- **GET /api/contatos/{id}**  -  Retorna os dados de um contato por ID
- **GET /api/contatos/pessoa/{idPessoa}**  -  Lista todos os contatos de uma pessoa
- **PUT /api/contatos/{id}**  -  Atualiza um contato existente
- **DELETE /api/contatos/{id}**  -  Remove um Contato por ID

## Documentação

A documentação da API é gerada automaticamente usando Swagger. Você pode acessar a documentação em: `http://localhost:8080/swagger-ui/`

## Tecnologias Utilizadas
-  Java 21
- Spring Boot 3.4.2
- Spring Web
- Spring Data JPA
- MySQL
- Swagger (para documentação da API)

## Configuração do Projeto

1.  Clone o repositório:

 ```bash
    git clone https://github.com/thiago-guerreiro/AppContatos.git
  ```


2.  Crie a base de dados no MySQL:

 ```sql
     CREATE DATABASE contatos;
  ```
 
3.  Atualize as properties no arquivo `src/main/resources/application.properties`  com seu username e password do MySQL

 ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/contatos
    spring.datasource.username=seu-username
    spring.datasource.password=sua-password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
 ```

### Pré-requisitos
-  Java 21
- Maven 3.9.9
- MySQL 8
