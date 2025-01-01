# 🧰 Equip Manager

![image](https://github.com/user-attachments/assets/4b042680-19b7-4e58-b70e-44206f65ac46)


## Descrição

O **Equip Manager** é uma aplicação web projetada para gerenciar itens de inventário de forma simples e eficiente. Com um sistema de CRUD (Criar, Ler, Atualizar, Excluir), os usuários podem facilmente adicionar, listar, editar e remover itens de um inventário.

![image](https://github.com/user-attachments/assets/e282dc79-ed68-4373-b31c-30338da1ef8f)

## Como rodar o projeto

### 1. Levantar a infraestrutura com Docker Compose

Execute o seguinte comando para levantar o PostgreSQL e criar toda a estrutura necessária:

```bash
docker-compose up
```

### 2. Rodar o projeto Java

No diretório do seu projeto Java, execute o comando abaixo para iniciar o aplicativo:

```bash
./mvnw spring-boot:run
```

### 3. Configurar e rodar o projeto Vue

No diretório do seu projeto Vue, execute os seguintes comandos:

1. Instalar as dependências:

```bash
npm install
```

2. Iniciar o servidor de desenvolvimento:

```bash
npm run serve
```

## 💻 Tecnologias Utilizadas

- **Frontend:** [Vue.js](https://vuejs.org/) - Framework JavaScript para criação da interface de usuário.
- **Backend:** [Spring Boot](https://spring.io/projects/spring-boot) - Framework Java para desenvolvimento de APIs RESTful.
- **Banco de Dados:** [PostgreSQL 16](https://www.postgresql.org/) - Sistema de gerenciamento de banco de dados relacional.
- **Bibliotecas e Ferramentas:**
  - [Guava](https://github.com/google/guava) - Biblioteca de utilitários para Java.
  - [jQuery](https://jquery.com/) - Biblioteca JavaScript para facilitar o desenvolvimento de interfaces.
  - [Validator](https://github.com/validatorjs/validator.js) - Biblioteca de validação para dados no frontend.
- **Java:** [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
