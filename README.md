# Keep Inventory - Sistema de Gerenciamento de Inventário Hospitalar

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.5.6
- PostgreSQL 16
- Gradle
- Swagger/OpenAPI
- Docker Compose

## 📋 Pré-requisitos

- Java 17+
- Docker & Docker Compose
- Gradle (ou use o wrapper incluído)

## 🏃 Como Executar

### 1. Subir o Banco de Dados

```bash
docker-compose up -d
```

Isso irá:
- Criar container PostgreSQL
- Criar database `dasa_keepinventory`
- Expor porta 5432

### 2. Verificar se o banco está rodando

```bash
docker ps
```

### 3. Executar a Aplicação

**Usando Gradle Wrapper (recomendado):**
```bash
./gradlew bootRun
```

**Ou usando Gradle instalado:**
```bash
gradle bootRun
```

### 4. Acessar a Aplicação

- **API Base URL**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8080/api-docs

## 🧪 Executar Testes

```bash
./gradlew test
```

## 📦 Gerar JAR

```bash
./gradlew build
```

O JAR será gerado em: `build/libs/keep-inventory-1.0.0.jar`

## 🔧 Configuração de Profiles

### Desenvolvimento (padrão)
```bash
./gradlew bootRun
```

### Produção
```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```

## 🗄️ Conectar ao Banco via Cliente

**Usando psql:**
```bash
docker exec -it dasa_keepinventory psql -U postgres -d dasa_keepinventory
```

**Usando cliente GUI (DBeaver, pgAdmin, etc):**
- Host: localhost
- Port: 5432
- Database: dasa_keepinventory
- User: postgres
- Password: postgres

## 📚 Estrutura da Aplicação

```
com.dasa.keepinventory/
├── domain/              # Núcleo do negócio (DDD)
├── application/         # Casos de uso (CQRS)
├── infrastructure/      # Persistência e detalhes técnicos
├── api/                 # Controllers REST (versionados)
└── shared/              # Código compartilhado
```

## 🎯 Endpoints Principais

### Materiais
- `POST   /api/v1/materiais` - Criar material
- `GET    /api/v1/materiais` - Listar materiais
- `GET    /api/v1/materiais/{id}` - Buscar por ID
- `GET    /api/v1/materiais/criticos` - Listar críticos
- `DELETE /api/v1/materiais/{id}` - Deletar material

### Estoque
- `POST   /api/v1/estoque/entrada` - Registrar entrada
- `POST   /api/v1/estoque/saida` - Registrar saída

### Consumo
- `POST   /api/v1/consumo` - Registrar consumo

## 🛠️ Troubleshooting

### Erro de conexão com PostgreSQL
```bash
docker ps

docker logs dasa_keepinventory

docker-compose restart
```

### Porta 5432 já em uso
```bash
lsof -i :5432

brew services stop postgresql  # Mac
sudo service postgresql stop   # Linux
```

### Limpar e recriar banco
```bash
docker-compose down -v

docker-compose up -d
```

## 📖 Documentação Adicional

Acesse a documentação interativa via Swagger:
http://localhost:8080/swagger-ui.html

## 👥 Autores

Time DASA - Keep Inventory

## 📄 Licença

Proprietary - DASA
