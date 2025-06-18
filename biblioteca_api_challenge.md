## 📋 Sobre o Desafio

Desenvolva uma **API REST completa** para gerenciar uma biblioteca utilizando **Java Spring Boot**. O sistema deve controlar livros, usuários e empréstimo.

---

## 🎯 Funcionalidades Requeridas

### **Gerenciamento de Livros**
- Cadastrar, consultar, atualizar e remover livros
- Listar livros com paginação e ordenação
- Buscar livros por categoria
- Controle de disponibilidade

### **Gerenciamento de Usuários**
- Cadastrar e consultar usuários
- Validação de dados (email único)
- Status ativo/inativo

### **Sistema de Empréstimos**
- Registrar empréstimos (validando disponibilidade)
- Processar devoluções
- Histórico de empréstimos por usuário
- Controle de prazos e status

---

## 🔗 Endpoints Obrigatórios

| Método   | Endpoint                            | Descrição                |
|----------|-------------------------------------|--------------------------|
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

## 🏗️ Estrutura de Dados

### **Livro**
```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "categoria": "PROGRAMACAO",
  "anoPublicacao": 2008,
  "disponivel": true
}
```

### **Usuário**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
}
```

### **Empréstimo**
```json
{
  "id": 1,
  "usuarioId": 1,
  "livroId": 1,
  "dataEmprestimo": "2024-01-20T09:00:00",
  "dataPrevistaDevolucao": "2024-02-03T09:00:00",
  "dataDevolucao": null,
  "status": "EMPRESTADO"
}
```


