# KeepInventory

## Introdução
KeepInventory é um sistema de gestão de estoque para controle de materiais em almoxarifados e consumo em unidades organizacionais. Permite registrar entradas, saídas e transferências de materiais, além de gerar relatórios de consumo. Funciona como um protótipo de ERP leve com API REST.

---

## Pré-requisitos
- Java 17 (JDK)
- Gradle 8.x
- PostgreSQL
- Docker
---

## Configuração do Banco de Dados
1. Rode o Docker Compose:
```bash
$ docker compose up -d
```
1.1 Crie o banco manualmente se precisar no PostgreSQL:
```sql
CREATE DATABASE keepinventory;
```
2. Atualize se necessário `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dasa_keepinventory
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

---

## Build & Execução

1. Build do projeto:
```bash
./gradlew clean build
```

2. Executar a aplicação:
```bash
./gradlew bootRun
```
A aplicação estará disponível em `http://localhost:8080`.

---

## Executando Testes
- Rodar testes:
```bash
./gradlew test
```

- Rodar teste específico:
```bash
./gradlew test --tests "com.dasa.keepinventory.services.EstoqueServiceTest.deveCalcularEstoqueAtualComEntradasESaidas"
```

Relatórios de teste ficam em `build/reports/tests/test/index.html`.

---

## Endpoints REST

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET    | /materiais | Listar todos os materiais |
| GET    | /materiais/{id} | Obter material pelo ID |
| POST   | /materiais | Criar um novo material |
| DELETE | /materiais/{id} | Deletar material pelo ID |
| GET    | /almoxarifados | Listar todos os almoxarifados |
| GET    | /almoxarifados/{id} | Obter almoxarifado pelo ID |
| POST   | /almoxarifados | Criar um novo almoxarifado |
| DELETE | /almoxarifados/{id} | Deletar almoxarifado pelo ID |
| GET    | /movimentacoes | Listar todas as movimentações de estoque |
| GET    | /movimentacoes/{id} | Obter movimentação pelo ID |
| POST   | /movimentacoes | Registrar entrada, saída ou transferência |
| DELETE | /movimentacoes/{id} | Deletar movimentação pelo ID |
| GET    | /registros | Listar registros de consumo |
| GET    | /registros/{id} | Obter registro de consumo pelo ID |
| POST   | /registros | Registrar consumo de material |
| DELETE | /registros/{id} | Deletar registro de consumo pelo ID |
| GET    | /unidades | Listar unidades organizacionais |
| GET    | /unidades/{id} | Obter unidade pelo ID |
| POST   | /unidades | Criar nova unidade |
| DELETE | /unidades/{id} | Deletar unidade pelo ID |
| GET    | /responsaveis | Listar responsáveis por movimentações |
| GET    | /responsaveis/{id} | Obter responsável pelo ID |
| POST   | /responsaveis | Criar novo responsável |
| DELETE | /responsaveis/{id} | Deletar responsável pelo ID |
| GET    | /estoque/{idMaterial}/atual | Retorna a quantidade atual em estoque do material especificado |
| POST   | /estoque/{idMaterial}/consumo | Registra consumo de um material específico |
| GET    | /estoque/{idMaterial}/reposicao | Verifica se o material precisa de reposição |
| GET    | /estoque/{idMaterial}/movimentacoes?inicio={dataInicio}&fim={dataFim} | Busca movimentações do material em um período específico |

---

## Exemplos com cURL

```bash
# Criar um novo material
curl -X POST http://localhost:8080/materiais \
  -H "Content-Type: application/json" \
  -d '{"nome":"Paracetamol","categoria":"Medicamento","unidadeMedida":"kg","quantidadeTotal":100}'

# Listar todos os materiais
curl -X GET http://localhost:8080/materiais
```

---

## Observações
- Certifique-se que o driver PostgreSQL está incluído no \`build.gradle\`:
```gradle
implementation 'org.postgresql:postgresql:42.6.0'
```
- Se aparecer erro `Cannot load driver class: org.postgresql.Driver`, verifique a dependência e a configuração do banco.
- Todas as entidades do banco (`Almoxarifado`, `Material`, `MovimentacaoEstoque`, `RegistroConsumo`, `Responsavel`, `Unidade`) possuem endpoints CRUD básicos conforme tabela de endpoints acima.

---

## Estrutura do Projeto

```
src/
 ├─ main/
 │   ├─ java/com/dasa/keepinventory/
 │   │   ├─ api/
 │   │   ├─ services/
 │   │   ├─ dao/
 │   │   ├─ models/
 │   │   └─ KeepinventoryApplication.java
 │   └─ resources/
 │       └─ application.properties
 └─ test/
     └─ java/com/dasa/keepinventory/services
         └─ EstoqueServiceTest.java
```

---

## Considerações Finais
O projeto é um protótipo funcional de gestão de estoque com API REST. Ele permite integração futura com ERPs maiores, como SAP, e pode ser expandido para incluir autenticação, relatórios detalhados e dashboards.
