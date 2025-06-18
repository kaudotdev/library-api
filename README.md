<h1 align="center">📚 Biblioteca API 📚</h1>

<p align="center">
  Uma API RESTful completa para gerenciamento de uma biblioteca, desenvolvida com Java e Spring Boot.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-blue?style=for-the-badge&logo=openjdk" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen?style=for-the-badge&logo=spring" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Maven-4.0.0-red?style=for-the-badge&logo=apache-maven" alt="Maven">
  <img src="https://img.shields.io/badge/Status-Em%20Desenvolvimento-orange?style=for-the-badge" alt="Status">
</p>

---

## 📖 Sobre o Projeto

Este projeto é uma API REST projetada para gerenciar um sistema de biblioteca de forma eficiente. Ele permite o controle completo sobre livros, usuários e o processo de empréstimos, tudo isso com segurança gerenciada por Spring Security e OAuth2.

---

## 🎯 Funcionalidades

-   **Gerenciamento de Livros**:
    -   CRUD completo (Cadastrar, consultar, atualizar e remover).
    -   Listagem com paginação e ordenação.
    -   Busca por categoria e controle de disponibilidade.
-   **Gerenciamento de Usuários**:
    -   Cadastro e consulta de usuários.
    -   Validação de dados (ex: email único).
-   **Sistema de Empréstimos**:
    -   Registro de empréstimos com validação de disponibilidade de livros.
    -   Processamento de devoluções.
    -   Histórico de empréstimos por usuário.

---

## 🚀 Como Começar

Siga estas instruções para ter o projeto rodando em sua máquina local para desenvolvimento e teste.

### 📋 Pré-requisitos

Você vai precisar ter o seguinte software instalado:

-   [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
-   [Apache Maven](https://maven.apache.org/download.cgi)
-   Um cliente Git (como o [Git](https://git-scm.com/))

### ⚙️ Instalação

1.  Clone o repositório:
    ```sh
    git clone https://github.com/kaudotdev/library-api.git
    ```
2.  Navegue até o diretório do projeto:
    ```sh
    cd library-api
    ```
3.  Instale as dependências do Maven:
    ```sh
    mvn install
    ```

---

## ▶️ Como Executar a Aplicação

Você pode executar a aplicação usando o plugin do Spring Boot para Maven. O servidor será iniciado e a API estará pronta para receber requisições.

```sh
mvn spring-boot:run
```

> A API estará disponível em `http://localhost:8080`.

---

## ⚡ Endpoints da API

Abaixo estão os endpoints disponíveis para interagir com a API:

| Método   | Endpoint                            | Descrição                |
| :------- | :---------------------------------- | :----------------------- |
| `GET`    | `/api/livros`                       | Listar livros (paginado) |
| `GET`    | `/api/livros/{id}`                  | Buscar livro por ID      |
| `POST`   | `/api/livros`                       | Cadastrar livro          |
| `PUT`    | `/api/livros/{id}`                  | Atualizar livro          |
| `DELETE` | `/api/livros/{id}`                  | Remover livro            |
| `GET`    | `/api/livros/categoria/{categoria}` | Buscar por categoria     |
| `POST`   | `/api/emprestimos`                  | Registrar empréstimo     |
| `PUT`    | `/api/emprestimos/{id}/devolucao`   | Registrar devolução      |
| `GET`    | `/api/usuarios/{id}/emprestimos`    | Histórico de empréstimos |

---

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias e ferramentas:

-   **Backend**: Java 21, Spring Boot 3.5.0
-   **Acesso a Dados**: Spring Data JPA
-   **Segurança**: Spring Security, OAuth2 (Authorization & Resource Server)
-   **Banco de Dados**: MySQL (com MySQL Connector)
-   **Ferramentas**: Lombok, Maven

---

## 🤝 Contribuição

Contribuições são o que tornam a comunidade de código aberto um lugar incrível para aprender, inspirar e criar. Qualquer contribuição que você fizer será **muito apreciada**.

1.  Faça um Fork do projeto
2.  Crie sua Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Faça o Commit de suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4.  Faça o Push para a Branch (`git push origin feature/AmazingFeature`)
5.  Abra um Pull Request

---

## 📄 Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.

---

<p align="center">
  Feito com ❤️ por <a href="https://github.com/kaudotdev">kaudotdev</a>
</p>
