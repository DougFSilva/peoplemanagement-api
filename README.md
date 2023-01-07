# API para gerenciamento de pessoas
Essa API é uma proposta de teste para uma vaga de emprego para Desenvolvedor Java jr. Ela tem como propósito fazer o gerenciamento de pessoas, permitindo criar, editar, buscar e remover pessoas e adicionar, remover e editar endereços das mesmas.

## 🚀 Começando

### 1. Configurar o local do arquivo de log

Para isso basta editar a tag **file** do arquivo chamado *logback-spring.xml* que se encontra em *src/main/resources*.

### 2. Gerar o jar da aplicação

Com o comando *mvn clean package* o arquivo jar será gerado dentro da pasta *target*

### 3. Editar o arquivo docker-compose.yml

Editar a porta e o caminho do volume onde será gerado o arquivo de log. Por padrão a porta está configurada para **8083** e o path para arquivo de log para **/app/peoplemanagement/logs**

### 4. Rodar o docker compose

Com o comando *docker compose up -d* a aplicação iniciará.

## 🛠️Construído com

* Spring boot
* Banco H2
* SLF4J
* Swagger
* Docker
* Clean architeture
* Junit
* Mockito
---
## ✒️ Autor
* Douglas Ferreira da Silva


