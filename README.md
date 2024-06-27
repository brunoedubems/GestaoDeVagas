# Gestão de Vagas - API Rest

## Descrição
Este projeto consiste em uma API Rest para um site de vagas de emprego. A API permite gerenciar vagas de emprego, candidatos, e todas as operações relacionadas a um sistema de recrutamento.

## Funcionalidades Principais
- CRUD de Vagas
- CRUD de Candidatos
- Busca de Vagas por critérios diversos
- Inscrição de Candidatos em Vagas

## Tecnologias Utilizadas
- Java
- Spring Boot
- Hibernate/JPA para persistência de dados
- Banco de Dados relacional (PostgreSQL)

## Metodologias e Princípios de Design Utilizados
- DTO (Data Transfer Objects) para transferência de dados entre camadas
- TDD (Test-Driven Development) para desenvolvimento orientado a testes
- SOLID e princípios de design para um código limpo e de qualidade
- Testes Unitários com JUnit (ou outra ferramenta de testes)
- Documentação automática da API com Swagger (ou similar)
- Integração Contínua com Jenkins (ou outra ferramenta CI)

## Estrutura do Projeto
- `src/`
  - `main/`
    - `java/`
      - `com.example.gestaodevagas/`
        - `controller/` - Controladores da API
        - `service/` - Lógica de negócios
        - `repository/` - Camada de acesso a dados
        - `model/` - Entidades do sistema
        - `dto/` - Data Transfer Objects
        - `exception/` - Tratamento de exceções
    - `resources/`
      - `application.properties` - Configurações do Spring Boot
  - `test/`
    - `java/`
      - `com.example.gestaodevagas/`
        - `controller/` - Testes dos controladores
        - `service/` - Testes dos serviços
        - `repository/` - Testes dos repositórios
        - `util/` - Classes utilitárias para testes

## Como Executar
1. Clone o repositório
2. Importe o projeto na sua IDE preferida
3. Configure o banco de dados conforme as configurações em `application.properties`
4. Execute a aplicação

## Documentação da API
A documentação da API pode ser acessada através de `{url-da-sua-api}/swagger-ui.html`.

## Licença
Este projeto está licenciado sob a [MIT License](LICENSE).
