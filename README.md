# Keep Inventory API - Documentação Completa

## 📋 Introdução

O **Keep Inventory** é uma API REST desenvolvida para gerenciamento de inventário e controle de estoque hospitalar. O sistema permite o controle completo de materiais médicos, movimentações de estoque, registro de consumo e monitoramento de níveis críticos de materiais.

Esta documentação fornece uma visão completa do projeto, desde sua arquitetura até instruções de instalação e uso das rotas disponíveis.

---

## 🎯 Explicação do Projeto

### Objetivo

Criar um sistema robusto para hospitais gerenciarem:
- **Materiais**: Cadastro e controle de itens médicos (luvas, máscaras, medicamentos, etc.)
- **Estoque**: Entradas e saídas de materiais em almoxarifados
- **Consumo**: Registro de uso de materiais por unidades hospitalares
- **Alertas**: Identificação automática de materiais em níveis críticos
- **Rastreabilidade**: Histórico completo de movimentações e responsáveis

### Contexto de Uso

O sistema é ideal para:
- Hospitais que precisam controlar múltiplos almoxarifados
- Gestores que necessitam visibilidade sobre níveis de estoque
- Equipes que precisam rastrear consumo por unidade/setor
- Administradores que querem evitar falta de materiais críticos

### Diferenciais

- **Regras de negócio no domínio**: Lógica complexa encapsulada em specifications
- **Arquitetura limpa**: Separação clara de responsabilidades
- **Rastreabilidade total**: Cada operação registra responsável e timestamp
- **Validações em múltiplas camadas**: Desde DTOs até regras de domínio

---

## 🏗️ Arquitetura e Decisões

### Visão Geral da Arquitetura

O projeto segue os princípios de **Domain-Driven Design (DDD)** e **Clean Architecture**, organizados em camadas bem definidas:
```
┌─────────────────────────────────────────────────────────┐
│                    API Layer (Controllers)              │
│  - Recebe requisições HTTP                              │
│  - Valida DTOs                                          │
│  - Delega para Application Layer                        │
└─────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────┐
│              Application Layer (CQS)                    │
│  - Commands: Operações de escrita                       │
│  - Queries: Operações de leitura                        │
│  - Handlers: Orquestração de casos de uso               │
└─────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────┐
│                  Domain Layer                           │
│  - Entities: Material, Almoxarifado, etc.               │
│  - Aggregates: MovimentacaoEstoque, RegistroConsumo     │
│  - Value Objects: Quantidade, InformacaoMaterial        │
│  - Specifications: Regras de negócio reutilizáveis      │
│  - Repositories (Interfaces)                            │
└─────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────┐
│            Infrastructure Layer                         │
│  - JPA Entities                                         │
│  - Repository Implementations                           │
│  - Mappers (Domain ↔ JPA)                               │
│  - Database Configuration                               │
└─────────────────────────────────────────────────────────┘
```

### Decisões Arquiteturais

#### 1. **CQS (Command Query Separation)**

**O que é:**
Separação entre operações de leitura (Queries) e escrita (Commands).

**Por que usamos:**
- **Clareza**: Código mais legível e organizado
- **Escalabilidade**: Fácil otimizar leituras e escritas separadamente
- **Manutenibilidade**: Cada handler tem uma única responsabilidade
- **Testabilidade**: Handlers podem ser testados isoladamente

**Exemplo prático:**
```java
// Command (escrita)
CriarMaterialCommand → CriarMaterialCommandHandler

// Query (leitura)
ObterTodosMateriaisQuery → ObterTodosMateriaisQueryHandler
```

#### 2. **Domain-Driven Design (DDD)**

**Conceitos aplicados:**

- **Entities**: Objetos com identidade única (Material, Responsavel)
- **Value Objects**: Objetos imutáveis sem identidade (Quantidade, Localizacao)
- **Aggregates**: Clusters de entidades tratadas como unidade (MovimentacaoEstoque)
- **Specifications**: Regras de negócio encapsuladas e reutilizáveis
- **Repositories**: Abstração para persistência

**Benefícios:**
- Código reflete a linguagem do negócio (Ubiquitous Language)
- Regras de negócio centralizadas no domínio
- Facilita mudanças nos requisitos
- Reduz acoplamento com infraestrutura

#### 3. **Specification Pattern**

**O que faz:**
Encapsula regras de negócio complexas em objetos reutilizáveis e combináveis.

**Exemplo no código:**
```java
// No Material
public final RegraPodeSerConsumidoComSeguranca podeSerConsumidoComSeguranca;

// Uso
boolean seguro = material.podeSerConsumidoComSeguranca
    .satisfeitoPor(quantidadeSolicitada, nivelCritico);
```

**Benefícios:**
- Regras legíveis e autodocumentadas
- Fácil combinação (E, OU, NÃO)
- Testável isoladamente
- Reutilizável em diferentes contextos

#### 4. **Builder Pattern**

**Por que usamos:**
- Criação de objetos complexos de forma fluente
- Validações centralizadas
- Código mais legível

**Exemplo:**
```java
Material material = Material.builder()
    .nome("Luvas")
    .categoria("EPI")
    .unidadeMedida("UN")
    .quantidadeTotal(100.0)
    .build();
```

#### 5. **Result Pattern**

**O que resolve:**
Trata erros sem usar exceptions para fluxo de controle.

**Benefícios:**
- Erros são parte do contrato da API
- Melhor performance (sem stack traces)
- Código mais funcional e previsível
- Facilita composição de operações

**Exemplo:**
```java
Result<Material> result = handler.handle(command);
if (result.isSuccess()) {
    // sucesso
} else {
    // erro conhecido
}
```

#### 6. **Separação Domain ↔ Infrastructure**

**Como funciona:**
```
Domain (Material) → Mapper → Infrastructure (MaterialJpaEntity)
```

**Por que é importante:**
- Domínio puro, sem dependências do JPA
- Fácil trocar banco de dados
- Testes de domínio não precisam de banco
- Evita poluir modelo de negócio com anotações técnicas

### Estrutura Física do Projeto
```
keepinventory/
├── src/
│   ├── main/
│   │   ├── java/com/dasa/keepinventory/
│   │   │   ├── api/                           # Camada de API
│   │   │   │   ├── config/                    # Configurações (Swagger, Exception Handler)
│   │   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   │   └── OpenApiConfig.java
│   │   │   │   ├── controllers/v1/            # Controllers REST
│   │   │   │   │   ├── ConsumoController.java
│   │   │   │   │   ├── EstoqueController.java
│   │   │   │   │   └── MaterialController.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── request/               # DTOs de entrada
│   │   │   │   │   │   ├── CreateMaterialRequest.java
│   │   │   │   │   │   ├── UpdateMaterialRequest.java
│   │   │   │   │   │   └── ...
│   │   │   │   │   └── response/              # DTOs de saída
│   │   │   │   │       ├── ApiResponse.java
│   │   │   │   │       ├── MaterialResponse.java
│   │   │   │   │       └── ...
│   │   │   │   └── mappers/                   # Mappers (Domain → Response)
│   │   │   │       ├── MaterialResponseMapper.java
│   │   │   │       └── ResultToApiResponseMapper.java
│   │   │   │
│   │   │   ├── application/                   # Camada de Aplicação (CQS)
│   │   │   │   └── cqs/
│   │   │   │       ├── base/                  # Interfaces base
│   │   │   │       │   ├── Command.java
│   │   │   │       │   ├── CommandHandler.java
│   │   │   │       │   ├── Query.java
│   │   │   │       │   └── QueryHandler.java
│   │   │   │       ├── commands/              # Commands por agregado
│   │   │   │       │   ├── material/
│   │   │   │       │   │   ├── CriarMaterialCommand.java
│   │   │   │       │   │   ├── AtualizarMaterialCommand.java
│   │   │   │       │   │   └── DeletarMaterialCommand.java
│   │   │   │       │   ├── estoque/
│   │   │   │       │   └── consumo/
│   │   │   │       ├── queries/               # Queries por agregado
│   │   │   │       │   └── material/
│   │   │   │       │       ├── ObterTodosMateriaisQuery.java
│   │   │   │       │       └── ListarMateriaisCriticosQuery.java
│   │   │   │       └── handlers/              # Handlers (Casos de uso)
│   │   │   │           ├── material/
│   │   │   │           │   ├── CriarMaterialCommandHandler.java
│   │   │   │           │   ├── AtualizarMaterialCommandHandler.java
│   │   │   │           │   └── ObterTodosMateriaisQueryHandler.java
│   │   │   │           ├── estoque/
│   │   │   │           └── consumo/
│   │   │   │
│   │   │   ├── domain/                        # Camada de Domínio (Coração do sistema)
│   │   │   │   ├── aggregates/                # Agregados (raízes de consistência)
│   │   │   │   │   ├── MovimentacaoEstoque.java
│   │   │   │   │   └── RegistroConsumo.java
│   │   │   │   ├── entities/                  # Entidades do domínio
│   │   │   │   │   ├── Material.java
│   │   │   │   │   ├── Almoxarifado.java
│   │   │   │   │   ├── Responsavel.java
│   │   │   │   │   └── Unidade.java
│   │   │   │   ├── valueobjects/              # Value Objects (imutáveis)
│   │   │   │   │   ├── Quantidade.java
│   │   │   │   │   ├── InformacaoMaterial.java
│   │   │   │   │   ├── Localizacao.java
│   │   │   │   │   └── UnidadeMedida.java
│   │   │   │   ├── specifications/            # Regras de negócio
│   │   │   │   │   ├── base/                  # Pattern base
│   │   │   │   │   │   ├── Specification.java
│   │   │   │   │   │   ├── ESpecification.java
│   │   │   │   │   │   ├── OuSpecification.java
│   │   │   │   │   │   └── NaoSpecification.java
│   │   │   │   │   ├── material/              # Specs de Material
│   │   │   │   │   │   ├── EstaCriticamenteBaixoSpec.java
│   │   │   │   │   │   ├── PossuiEstoqueSuficienteSpec.java
│   │   │   │   │   │   └── EstaSemEstoqueSpec.java
│   │   │   │   │   └── movimentacao/          # Specs de Movimentação
│   │   │   │   ├── builders/                  # Builders para entidades
│   │   │   │   │   ├── base/
│   │   │   │   │   ├── MaterialBuilder.java
│   │   │   │   │   └── AlmoxarifadoBuilder.java
│   │   │   │   └── repositories/              # Interfaces de repositório
│   │   │   │       ├── MaterialRepository.java
│   │   │   │       ├── AlmoxarifadoRepository.java
│   │   │   │       └── ...
│   │   │   │
│   │   │   ├── infrastructure/                # Camada de Infraestrutura
│   │   │   │   └── persistence/
│   │   │   │       ├── entities/              # Entidades JPA
│   │   │   │       │   ├── BaseJpaEntity.java
│   │   │   │       │   ├── MaterialJpaEntity.java
│   │   │   │       │   └── ...
│   │   │   │       ├── jpa/                   # Repositories Spring Data
│   │   │   │       │   ├── MaterialJpaRepository.java
│   │   │   │       │   └── ...
│   │   │   │       ├── mappers/               # Mappers (Domain ↔ JPA)
│   │   │   │       │   ├── MaterialMapper.java
│   │   │   │       │   └── ...
│   │   │   │       └── repositories/          # Implementações dos repos
│   │   │   │           ├── MaterialRepositoryImpl.java
│   │   │   │           └── ...
│   │   │   │
│   │   │   ├── shared/                        # Código compartilhado
│   │   │   │   ├── Result.java                # Result pattern
│   │   │   │   └── exceptions/                # Exceções customizadas
│   │   │   │       ├── DomainException.java
│   │   │   │       ├── EntityNotFoundException.java
│   │   │   │       ├── BusinessRuleException.java
│   │   │   │       └── ValidationException.java
│   │   │   │
│   │   │   └── KeepinventoryApplication.java  # Main class
│   │   │
│   │   └── resources/
│   │       ├── application.properties         # Configurações da aplicação
│   │       └── schema.sql                     # Scripts SQL (se necessário)
│   │
│   └── test/                                  # Testes
│       └── java/com/dasa/keepinventory/
│
├── build.gradle                               # Configuração Gradle
├── settings.gradle
├── compose.yaml                               # Docker Compose (PostgreSQL)
└── README.md
```

### Benefícios da Estrutura

1. **Modularidade**: Cada camada tem responsabilidade clara
2. **Testabilidade**: Domínio testável sem dependências externas
3. **Manutenibilidade**: Mudanças isoladas em camadas específicas
4. **Escalabilidade**: Fácil adicionar novos recursos seguindo os padrões
5. **Independência**: Domínio não conhece infraestrutura

---

## 📦 Dependências

### Principais Tecnologias

| Tecnologia | Versão | Propósito |
|------------|--------|-----------|
| **Java** | 17 | Linguagem base |
| **Spring Boot** | 3.3.5 | Framework web e DI |
| **Spring Data JPA** | 3.3.5 | Persistência |
| **PostgreSQL** | 16 | Banco de dados |
| **SpringDoc OpenAPI** | 2.6.0 | Documentação Swagger |
| **Gradle** | 8.x | Build tool |
| **Docker** | Latest | Containerização do PostgreSQL |

### Dependências do `build.gradle`
```gradle
dependencies {
    // Spring Boot Starters
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    
    // PostgreSQL Driver
    runtimeOnly 'org.postgresql:postgresql:42.7.3'
    
    // OpenAPI/Swagger Documentation
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0'
    
    // Testing
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.mockito:mockito-core'
    testImplementation 'org.mockito:mockito-junit-jupiter'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

### Por que essas dependências?

- **Spring Boot Web**: Controllers REST, JSON serialization
- **Spring Data JPA**: Abstração para banco de dados relacional
- **PostgreSQL**: Banco robusto, open-source, ideal para produção
- **Bean Validation**: Validação declarativa com annotations
- **SpringDoc**: Geração automática de documentação OpenAPI 3.0
- **Mockito/JUnit**: Framework de testes unitários

---

## 🚀 Instalação do Projeto

### Pré-requisitos

Certifique-se de ter instalado:
- **Java 17** ou superior ([Download](https://adoptium.net/))
- **Docker** e **Docker Compose** ([Download](https://www.docker.com/get-started))
- **Git** ([Download](https://git-scm.com/))

### Passo 1: Clone o repositório
```bash
git clone https://github.com/seu-usuario/keepinventory.git
cd keepinventory
```

### Passo 2: Inicie o banco de dados PostgreSQL

O projeto inclui um arquivo `compose.yaml` para subir o PostgreSQL automaticamente:
```bash
docker-compose up -d
```

**O que isso faz:**
- Cria um container PostgreSQL na porta `5432`
- Cria o banco de dados `dasa_keepinventory`
- Usuário: `postgres` / Senha: `postgres`

**Verificar se está rodando:**
```bash
docker ps
```

Você deve ver um container chamado `dasa_keepinventory`.

### Passo 3: Build do projeto

No Linux/Mac:
```bash
./gradlew clean build
```

No Windows:
```bash
gradlew.bat clean build
```

**O que acontece:**
- Baixa todas as dependências
- Compila o código
- Roda os testes
- Gera o JAR executável

### Passo 4: Execute a aplicação
```bash
./gradlew bootRun
```

Ou execute o JAR diretamente:
```bash
java -jar build/libs/keepinventory-1.0.0.jar
```

### Passo 5: Verifique se está rodando

**Teste a aplicação:**
```bash
curl http://localhost:8080/api/v1/materiais
```

**Acesse o Swagger UI:**
```
http://localhost:8080/swagger-ui.html
```

### Configurações (application.properties)

O arquivo está em `src/main/resources/application.properties`:
```properties
# Aplicação
spring.application.name=keep-inventory
server.port=8080

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/dasa_keepinventory
spring.datasource.username=postgres
spring.datasource.password=postgres

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Swagger
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

**Para ambiente de produção**, altere:
- `spring.jpa.hibernate.ddl-auto=validate` (não altere schema automaticamente)
- `spring.jpa.show-sql=false` (não logue SQL)
- Use variáveis de ambiente para credenciais

---

## 🛣️ Rotas

### Base URL
```
http://localhost:8080/api/v1
```

### Documentação Interativa

Acesse o Swagger UI para testar todas as rotas:
```
http://localhost:8080/swagger-ui.html
```

---

### 📦 Materiais

#### **POST** `/materiais` - Criar Material

**Request Body:**
```json
{
  "nome": "Luvas de Procedimento",
  "categoria": "EPI",
  "unidadeMedida": "UN",
  "quantidadeInicial": 100.0
}
```

**Response:** `201 Created`
```json
{
  "data": {
    "id": 1,
    "nome": "Luvas de Procedimento",
    "categoria": "EPI",
    "unidadeMedida": "UN",
    "quantidadeTotal": 100.0
  },
  "success": true
}
```

#### **GET** `/materiais` - Listar Todos os Materiais

**Response:** `200 OK`
```json
{
  "data": [
    {
      "id": 1,
      "nome": "Luvas de Procedimento",
      "categoria": "EPI",
      "unidadeMedida": "UN",
      "quantidadeTotal": 100.0
    }
  ],
  "success": true
}
```

#### **GET** `/materiais/{id}` - Buscar Material por ID

**Response:** `200 OK`
```json
{
  "data": {
    "id": 1,
    "nome": "Luvas de Procedimento",
    "categoria": "EPI",
    "unidadeMedida": "UN",
    "quantidadeTotal": 100.0
  },
  "success": true
}
```

#### **PUT** `/materiais/{id}` - Atualizar Material

**Request Body:**
```json
{
  "nome": "Luvas de Procedimento Tamanho M",
  "categoria": "EPI",
  "unidadeMedida": "CAIXA",
  "quantidadeTotal": 150.0
}
```

**Response:** `200 OK`
```json
{
  "data": {
    "id": 1,
    "nome": "Luvas de Procedimento Tamanho M",
    "categoria": "EPI",
    "unidadeMedida": "CAIXA",
    "quantidadeTotal": 150.0
  },
  "success": true
}
```

#### **DELETE** `/materiais/{id}` - Deletar Material

**Response:** `204 No Content`

#### **GET** `/materiais/criticos?nivelCritico=10.0` - Listar Materiais Críticos

**Query Params:**
- `nivelCritico` (opcional, default: 10.0)

**Response:** `200 OK`
```json
{
  "data": [
    {
      "id": 2,
      "nome": "Álcool Gel",
      "categoria": "Higiene",
      "unidadeMedida": "L",
      "quantidadeTotal": 5.0
    }
  ],
  "success": true
}
```

---

### 📊 Estoque

#### **POST** `/estoque/entrada` - Registrar Entrada de Estoque

**Request Body:**
```json
{
  "idMaterial": 1,
  "idAlmoxarifado": 1,
  "idResponsavel": 1,
  "quantidade": 50.0
}
```

**Response:** `201 Created`
```json
{
  "data": "Entrada registrada com sucesso",
  "success": true
}
```

#### **POST** `/estoque/saida` - Registrar Saída de Estoque

**Request Body:**
```json
{
  "idMaterial": 1,
  "idAlmoxarifado": 1,
  "idResponsavel": 1,
  "quantidade": 20.0
}
```

**Response:** `201 Created`
```json
{
  "data": "Saída registrada com sucesso",
  "success": true
}
```

---

### 📝 Consumo

#### **POST** `/consumo` - Registrar Consumo

**Request Body:**
```json
{
  "idMaterial": 1,
  "idUnidade": 1,
  "idResponsavel": 1,
  "quantidade": 10.0,
  "observacao": "Procedimento cirúrgico - sala 3"
}
```

**Response:** `201 Created`
```json
{
  "data": "Consumo registrado com sucesso",
  "success": true
}
```

---

### 📍 Almoxarifados

_(Seguem o mesmo padrão dos materiais: POST, GET, GET by ID, DELETE)_

### 👤 Responsáveis

_(Seguem o mesmo padrão dos materiais: POST, GET)_

### 🏥 Unidades

_(Seguem o mesmo padrão dos materiais: POST, GET)_

---

### Códigos de Status HTTP

| Código | Significado | Quando ocorre |
|--------|-------------|---------------|
| **200** | OK | Requisição bem-sucedida (GET, PUT) |
| **201** | Created | Recurso criado com sucesso (POST) |
| **204** | No Content | Recurso deletado com sucesso (DELETE) |
| **400** | Bad Request | Dados inválidos ou regra de negócio violada |
| **404** | Not Found | Recurso não encontrado |
| **500** | Internal Server Error | Erro inesperado no servidor |

---

### Exemplos de Erros

**Validação falhou:**
```json
{
  "error": "Erro de validação: {nome=Nome é obrigatório}",
  "success": false
}
```

**Entidade não encontrada:**
```json
{
  "error": "Material com ID 999 não encontrado",
  "success": false
}
```

**Estoque insuficiente:**
```json
{
  "error": "Estoque insuficiente para realizar a saída",
  "success": false
}
```

---

## 📝 Resumo

### O que foi construído

O **Keep Inventory** é uma API REST completa para gerenciamento hospitalar com:

✅ **CRUD completo** de materiais, almoxarifados, responsáveis e unidades  
✅ **Movimentações de estoque** com entrada e saída  
✅ **Registro de consumo** por unidade com rastreabilidade  
✅ **Regras de negócio** implementadas com Specification Pattern  
✅ **Arquitetura limpa** seguindo DDD e CQS  
✅ **Documentação automática** com Swagger/OpenAPI  
✅ **Validações em múltiplas camadas** (DTO, Domain, Business Rules)  

### Tecnologias principais

- **Backend**: Java 17, Spring Boot 3.3.5
- **Persistência**: Spring Data JPA, PostgreSQL 16
- **Documentação**: SpringDoc OpenAPI 2.6.0
- **Arquitetura**: DDD, CQS, Clean Architecture
- **Patterns**: Specification, Builder, Result, Repository

### Diferenciais técnicos

1. **Domínio rico**: Lógica de negócio no domínio, não em services anêmicos
2. **Separação de responsabilidades**: CQS mantém código organizado
3. **Testabilidade**: Domínio puro, sem dependências de framework
4. **Manutenibilidade**: Estrutura modular facilita evolução
5. **Documentação viva**: Swagger sempre sincronizado com o código

### Próximos passos sugeridos

- [ ] Implementar autenticação/autorização (Spring Security + JWT)
- [ ] Adicionar paginação e filtros nas listagens
- [ ] Criar relatórios de consumo por período
- [ ] Implementar notificações para materiais críticos
- [ ] Adicionar auditoria completa (quem/quando/o quê)
- [ ] Criar testes de integração e E2E
- [ ] Implementar cache com Redis
- [ ] Adicionar métricas e observabilidade (Micrometer + Prometheus)

### Contato e Suporte

**Desenvolvido por**: DASA  
**Email de suporte**: suporte@dasa.com.br  
**Documentação da API**: http://localhost:8080/swagger-ui.html  
**Versão**: 1.0.0

---

**Licença**: Apache 2.0

*Documentação gerada em Outubro de 2025*
