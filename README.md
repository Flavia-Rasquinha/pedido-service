<h1 align="center">
  🛵<p>Pedido Service</p>
</h1>
<p align="center">
  Microsserviços com comunicação por mensageria para gerenciamento de pedidos, desenvolvido como exercício de uma prova de conceito
</p>
<p align="center">
  <a href="https://github.com/Flavia-Rasquinha/pedido-service">pedido-service</a> · <a href="https://github.com/Flavia-Rasquinha/cozinha-service">cozinha-service</a>
</p>
<br>
<p align="center">
  <a href="#sobre">Sobre</a> |
  <a href="#instalação">Instalação</a> |
  <a href="#acessos">Acessos</a> |
  <a href="#endpoints">Endpoints</a>
</p>

<br>

# Sobre

Conforme a proposta do [enunciado](https://github.com/Flavia-Rasquinha/pedido-service#STATEMENT.md) foram desenvolvidos dois microsserviços, o **pedido-service** responsável pela criação e gerenciamento dos pedidos, com comunicação por mensageria com a **[cozinha-service](https://github.com/Flavia-Rasquinha/cozinha-service)**, onde é feito o gerenciamento dos itens da cozinha. Utiliza o Apache Kafka para enviar pedidos para a cozinha-service e aguarda a confirmação do status do pedido.

### Tecnologias Utilizadas
- Java 17 + Spring Boot 2.7.2
- Apache Kafka + Spring Kafka
- Lombok
- JUnit
- MongoDB + Flapdoodle Embedded
- Docker

### Serviços Utilizados
- [Render](https://render.com/) - Service hosting
- [Upstash](https://upstash.com/) - Serverless kafka hosting
- [Atlas](https://www.mongodb.com/atlas/database) - Database hosting 

<br>

# Instalação
As aplicações podem ser executadas em ambiente local fazendo uso do Docker, para subir as imagens basta executar o docker-compose do projeto e subir as aplicações java.

```bash
docker-compose up -d
```
<br>

# Acessos
Todos os serviços podem ser acessados online, sejam os microsserviços, ou então o console do Kafka e do MongoDB (estes mediante usuário/senha).
- [pedido-service](https://pedido-service.onrender.com)
- [cozinha-service](https://cozinha-service.onrender.com)
- [kafka](https://console.upstash.com/kafka/37e3fc6c-191d-4ca3-ad46-fdba4fd44dd8/03268c8b-a670-4010-94d7-67b4c2a936e1)
- [Atlas mongo](https://cloud.mongodb.com/v2/65d3e1126f8bb92563f4e1c5#/clusters)

<br>

# Endpoints
Para documentação completa dos endpoints acessar o **SwaggerUI**:<br>
**Local:** http://localhost:8080/swagger-ui/<br>
**Online:** https://pedido-service.onrender.com/swagger-ui/<br>

<br>

- `GET` /orders · Listar Pedidos
- `POST` /orders · Criar Pedido
- `GET` /orders/{id} · Mostrar Pedido
- `PATCH` /orders/{id} · Atualizar Status do Pedido
- `DELETE` /orders/{id} · Remover Pedido