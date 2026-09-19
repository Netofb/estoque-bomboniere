# 🍬 Estoque Bomboniere API

API REST para gerenciamento do estoque de uma bomboniere. Desenvolvida com Java, Spring Boot e PostgreSQL, ela permite organizar categorias e produtos, controlar entradas e saídas e consultar o histórico e indicadores do estoque.

## Funcionalidades

- CRUD completo de categorias e produtos;
- filtros de produtos por nome e categoria;
- controle de entrada e saída com histórico automático;
- bloqueio de saídas acima do estoque disponível;
- consulta de produtos em estoque baixo;
- resumo para dashboard;
- validação de dados e tratamento padronizado de erros;
- documentação interativa com Swagger/OpenAPI;
- testes unitários das regras de movimentação.

## Tecnologias

- Java 17
- Spring Boot 3.3
- Spring Web, Spring Data JPA e Bean Validation
- PostgreSQL 18
- Hibernate
- Maven local (`mvnw.cmd`)
- JUnit 5 e Mockito
- SpringDoc OpenAPI / Swagger

## Pré-requisitos

- Java 17 ou superior;
- PostgreSQL em execução;
- banco de dados chamado `bomboniere`.

O repositório contém um Maven local. Não é necessário instalar o comando `mvn` globalmente.

## Configuração do banco

Crie o banco, caso ele ainda não exista:

```sql
CREATE DATABASE bomboniere;
```

A API usa as seguintes variáveis de ambiente:

| Variável | Exemplo |
| --- | --- |
| `DATABASE_URL` | `jdbc:postgresql://localhost:5432/bomboniere` |
| `DATABASE_USERNAME` | `postgres` |
| `DATABASE_PASSWORD` | senha do PostgreSQL |

## Executar localmente

No PowerShell, o modo mais simples é:

```powershell
.\run-local.ps1
```

O script pede a senha do PostgreSQL sem armazená-la em arquivo. Alternativamente, defina as variáveis e execute:

```powershell
.\mvnw.cmd spring-boot:run
```

Depois de iniciar:

- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui/index.html`

## Testes

```powershell
.\mvnw.cmd test
```

## Executar com Docker

Com o Docker Desktop aberto, defina uma senha para o banco e inicie os dois serviços:

```powershell
$env:POSTGRES_PASSWORD = "uma-senha-segura"
docker compose up --build
```

O Compose inicia PostgreSQL e API, mantém os dados em um volume nomeado e só inicializa a API após a disponibilidade do banco. O PostgreSQL é exposto na porta `5433` por padrão, evitando conflito com uma instalação local na porta 5432; altere-a com `POSTGRES_PORT` se necessário. Para encerrar, use `docker compose down`. O status da aplicação pode ser consultado em `http://localhost:8080/actuator/health`.

## Endpoints

### Categorias

| Método | Rota | Descrição |
| --- | --- | --- |
| POST | `/api/categorias` | Cria categoria |
| GET | `/api/categorias` | Lista categorias |
| GET | `/api/categorias/{id}` | Busca categoria |
| PUT | `/api/categorias/{id}` | Atualiza categoria |
| DELETE | `/api/categorias/{id}` | Exclui categoria sem produtos |

### Produtos

| Método | Rota | Descrição |
| --- | --- | --- |
| POST | `/api/produtos` | Cria produto |
| GET | `/api/produtos` | Lista e filtra produtos |
| GET | `/api/produtos/{id}` | Busca produto |
| PUT | `/api/produtos/{id}` | Atualiza produto |
| DELETE | `/api/produtos/{id}` | Exclui produto |
| GET | `/api/produtos/estoque-baixo` | Lista itens para reposição |
| POST | `/api/produtos/{id}/entrada` | Registra entrada |
| POST | `/api/produtos/{id}/saida` | Registra saída |

Filtros disponíveis: `/api/produtos?nome=chocolate` e `/api/produtos?categoriaId=1`.

### Movimentações e dashboard

| Método | Rota | Descrição |
| --- | --- | --- |
| GET | `/api/movimentacoes` | Lista movimentações |
| GET | `/api/movimentacoes?tipo=ENTRADA` | Filtra por tipo |
| GET | `/api/movimentacoes/produto/{id}` | Histórico de um produto |
| GET | `/api/dashboard/resumo` | Totais do estoque |

## Exemplos de uso

Criar categoria:

```json
POST /api/categorias
{ "nome": "Chocolates" }
```

Criar produto:

```json
POST /api/produtos
{
  "nome": "Chocolate ao leite",
  "descricao": "Barra de 90g",
  "precoCompra": 4.50,
  "precoVenda": 7.00,
  "quantidade": 20,
  "estoqueMinimo": 5,
  "categoriaId": 1
}
```

Registrar saída:

```json
POST /api/produtos/1/saida
{ "quantidade": 2, "observacao": "Venda no balcão" }
```

## Resposta de erro

```json
{
  "status": 409,
  "erro": "Conflict",
  "mensagem": "Estoque insuficiente.",
  "timestamp": "2026-09-18T21:00:00"
}
```

## Estrutura

```text
src/main/java/com/bomboniere/estoque
├── controller     # Endpoints REST
├── dto            # Objetos de entrada e resposta
├── exception      # Tratamento global de erros
├── model          # Entidades JPA e enum
├── repository     # Acesso ao banco
└── service        # Regras de negócio
```

## Próximos passos

- autenticação com Spring Security e JWT;
- Docker e Docker Compose;
- testes de integração;
- CI/CD e deploy;
- frontend em React.
