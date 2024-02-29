## Pedido Service


Conforme o [enunciado](https://github.com/Flavia-Rasquinha/pedido-service#STATEMENT.md) foi desenvolvido duas apps.
O pedido-service é uma aplicação que permite a criação e gerenciamento de pedidos, possibilitando a comunicação 
entre o pedido e a [cozinha-service](https://cozinha-service.onrender.com) onde é feita o gerenciamento dos itens da cozinha.
Utiliza o Apache Kafka para enviar pedidos para a Cozinha Service e aguarda a confirmação do status do pedido.

**Tecnologias Utilizadas**

- Spring Boot
- Docker
- MongoDB
- Apache Kafka
- Flapdoodle Embedded Mongo
- Spring Kafka Test Embedded Kafka

**Deploy localhost**

Rodando a aplicação localhost, após rodar o comando **docker-compose up -d**, irá subir as imagens do kafka e mongo, após 
isso rodar a aplicação com o comando spring.profiles.active=local no environment para subir a aplicação com o profile local ativo.

**Servidores Implantados**

- [Render](https://render.com/) [pedido-service](https://pedido-service.onrender.com)
- [Render](https://render.com/) [cozinha-service](https://cozinha-service.onrender.com)
- [Upstash](https://upstash.com/) [kafka](https://console.upstash.com/kafka/37e3fc6c-191d-4ca3-ad46-fdba4fd44dd8/03268c8b-a670-4010-94d7-67b4c2a936e1)
- [Atlas mongo](https://cloud.mongodb.com/v2/65d3e1126f8bb92563f4e1c5#/clusters)

**Configurações**

* Porta da Aplicação: 8080
* URL do Kafka: choice-corgi-8216-us1-kafka.upstash.io:9092
* URL do MongoDB: mongodb+srv://flaviarask:<password>@cluster0.zd0tc3l.mongodb.net/restaurante

**Endpoints**

- Link para swagger caso tenha preferência https://pedido-service.onrender.com/swagger-ui/ 

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
````

* Endpoint para Atualizar Status do Pedido:
    * Método: PATCH
    * URL: https://pedido-service.onrender.com:443/order/{id}
    * Corpo da Requisição:

   ```json
    {
      "Ready" 
    }

*   Obter Status do Pedido:
* Método: GET
* URL: https://pedido-service.onrender.com:443/order/{id}


