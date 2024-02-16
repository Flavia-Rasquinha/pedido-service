## Pedido Service

**Descrição**

O Pedido Service é uma aplicação que permite a criação e gerenciamento de pedidos. Utiliza o Apache Kafka para enviar pedidos para a Cozinha Service e aguarda a confirmação do status do pedido.

**Tecnologias Utilizadas**

- Spring Boot
- Docker
- MongoDB
- Apache Kafka

**Instruções de Instalação**

1. Certifique-se de ter o Docker instalado.
2. Clone o repositório.
3. Execute `docker-compose up -d` para iniciar o MongoDB e o Kafka.
4. Execute a aplicação Spring Boot.

**Configurações**

* Porta da Aplicação: 8080
* URL do Kafka: localhost:29092
* URL do MongoDB: mongodb://localhost:27017/local

**Endpoints**

* Criar Pedido:
    * Método: POST
    * URL: http://localhost:8080/pedidos
    * Exemplo de Corpo da Requisição:

```json{
    "items" : [
    {
    "id": "1",
    "idDish": "alaminuta1",
    "amount": "1",
    "value": 10.0
    },
    {
    "id": "2",
    "idIngredient": "feijao1",
    "amount": "1",
    "value": 10.0
    }
    ],
    "totalValue": 10.0,
    "status":"Solicitado"
````

* Endpoint para Atualizar Status do Pedido:
    * Método: PATCH
    * URL: http://localhost:8080/order/{id}
    * Corpo da Requisição:

   ```json
    {
      "Pronto" 
    }

*   Obter Status do Pedido:
* Método: GET
* URL: http://localhost:8080/order/{id}


