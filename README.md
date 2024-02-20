## Pedido Service

**Descrição**

O Pedido Service é uma aplicação que permite a criação e gerenciamento de pedidos. Utiliza o Apache Kafka para enviar pedidos para a Cozinha Service e aguarda a confirmação do status do pedido.

**Tecnologias Utilizadas**

- Spring Boot
- Docker
- MongoDB
- Apache Kafka
 
**Servidores Implantados**

- Render pedido-service
- Render cozinha-service
- Upstash kafka
- Atlas mongo

**Configurações**

* Porta da Aplicação: 8080
* URL do Kafka: choice-corgi-8216-us1-kafka.upstash.io:9092
* URL do MongoDB: mongodb+srv://flaviarask:<password>@cluster0.zd0tc3l.mongodb.net/restaurante

**Endpoints**

* Criar Pedido:
    * Método: POST
    * URL: https://pedido-service.onrender.com:443/order
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
    * URL: https://pedido-service.onrender.com:443/order/{id}
    * Corpo da Requisição:

   ```json
    {
      "Pronto" 
    }

*   Obter Status do Pedido:
* Método: GET
* URL: https://pedido-service.onrender.com:443/order/{id}


