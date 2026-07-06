# ⭐ 7Stars - Plataforma de Avaliações

Sistema full-stack para cadastro, pesquisa e visualização de avaliações de empresas, produtos, pessoas, lugares e serviços.

Este projeto foi desenvolvido como parte do teste técnico de Desenvolvimento e Análise de Incidentes.

---

# 📷 Demonstração

## Home
- Listagem de avaliações
- Pesquisa em tempo real
- Exibição de notas através de estrelas

## Cadastro
- Cadastro de avaliações
- Seleção de categoria
- Sistema de nota através de estrelas
- Persistência em banco de dados

---

# 🏗 Arquitetura

```
7Stars
├── back-7stars
│   ├── Spring Boot
│   ├── Spring Data JPA
│   ├── SQLite
│   └── Swagger
│
└── front-7stars
    ├── Angular
    ├── TailwindCSS
    └── TypeScript
```

---

# 🚀 Tecnologias Utilizadas

## Backend
- Java 21
- Spring Boot
- Spring Data JPA
- SQLite
- Maven
- Swagger/OpenAPI

## Frontend
- Angular
- TypeScript
- TailwindCSS
- Signals API

---

# ⚙️ Executando o projeto

## Backend

Entre na pasta:

```bash
cd back-7stars
```

Execute:

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

---

## Frontend

Entre na pasta:

```bash
cd front-7stars
```

Instale as dependências:

```bash
npm install
```

Execute:

```bash
ng serve
```

A aplicação estará disponível em:

```text
http://localhost:4200
```

---

# 📚 Endpoints

## Listar avaliações

```http
GET /api/feedback
```

Resposta:

```json
[
  {
    "id":1,
    "nomeCriador":"Lucas",
    "nomeAvaliado":"Magalu",
    "categoria":"Empresa",
    "nota":7
  }
]
```

---

## Pesquisar avaliações

```http
GET /api/feedback/search?nome=magalu
```

---

## Cadastrar avaliação

```http
POST /api/feedback
```

Body:

```json
{
  "nomeCriador":"Lucas",
  "emailCriador":"lucas@email.com",
  "nomeAvaliado":"Magalu",
  "instagramAvaliado":"@magalu",
  "categoria":"Empresa",
  "nota":7,
  "comentario":"Excelente atendimento."
}
```

---

# ✅ Funcionalidades Implementadas

- [x] Cadastro de avaliações
- [x] Listagem de avaliações
- [x] Pesquisa dinâmica
- [x] Sistema de notas por estrelas
- [x] Categorias
- [x] Integração Front/Back
- [x] Persistência em banco
- [x] Logs básicos
- [x] Documentação da API

---

# 🔍 Parte 2 - Análise de Incidente

## Problema

Foi identificado aumento no número de erros durante operações de cadastro.

## Possíveis causas

- Falta de validação de entrada.
- Falta de tratamento de exceções.
- Timeout entre serviços.
- Consultas sem indexação.

## Correções propostas

- Implementação de validações.
- Tratamento global de exceções.
- Monitoramento via logs estruturados.
- Otimização de consultas.

## Medidas preventivas

- Testes automatizados.
- Observabilidade.
- Alertas de monitoramento.
- Revisão periódica de performance.

---

# 📝 Decisões Técnicas

### Frontend
Foi utilizado Angular com Signals devido à simplicidade de gerenciamento de estado e boa integração com aplicações SPA.

### Backend
Foi utilizado Spring Boot com Spring Data JPA visando rapidez de desenvolvimento, baixo acoplamento e facilidade de manutenção.

### Banco de Dados
Foi utilizado SQLite devido à simplicidade de configuração e adequação ao escopo do teste.

---

# 🚧 Melhorias Futuras

- Autenticação JWT.
- Upload de imagens.
- Paginação.
- Filtros avançados.
- Dashboard analítico.
- Testes E2E.
- Docker Compose.
- Cache.

---

# 👨‍💻 Autor

Lucas Macedo

- GitHub: https://github.com/llucasmaced
- LinkedIn: https://linkedin.com/in/llucasmaced
