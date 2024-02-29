### Prova de Conceito - Sistema de Restaurante com Microservices, Kafka, MongoDB e Spring Boot

**Objetivo**: demonstrar a implementação de um sistema de restaurante utilizando arquitetura de microsserviços, 
comunicação assíncrona com Kafka, persistência de dados no MongoDB, Spring Boot como framework e JUnit para testes 
automatizados.

**Estrutura do Sistema:**

O sistema de restaurante será composto por dois microsserviços:

1. **Pedido Service (Pedido-Service):**
   - Responsável pelo gerenciamento de pedidos.
   - Deve receber pedidos do cliente.
   - Deve publicar eventos de novos pedidos no tópico "pedidos" no Kafka.
   - Armazenar informações dos pedidos no MongoDB.

2. **Cozinha Service (Cozinha-Service):**
   - Responsável por processar os pedidos.
   - Deve consumir eventos do tópico "pedidos" no Kafka.
   - Processar os pedidos e publicar eventos de pedidos prontos no tópico "pedidos-prontos" no Kafka.

**Requisitos Técnicos:**

1. Utiliza pelo menos a versão LTS do Java para o desenvolvimento.
2. Utilize o Spring Boot 2.7.2 para o desenvolvimento dos microsserviços.
3. Configure a comunicação entre os microsserviços utilizando o Apache Kafka.
4. Utilize o MongoDB para armazenar informações sobre os pedidos.
5. Implemente testes unitários utilizando JUnit para ambos os serviços.

**Entregáveis:**

1. Código fonte completo dos microsserviços.
2. Configuração do ambiente (Docker Compose, se necessário).
3. Relatório técnico explicando as escolhas de arquitetura, tecnologias utilizadas e descrição dos testes realizados.
4. Bônus (opcional): publicar os serviços em algum cloud provider, configurando as esteiras de CI/CD via GitHub Actions.

**Avaliação:**

Será avaliada a correta implementação dos microsserviços, a integração eficiente com o Kafka e o MongoDB, além da qualidade 
dos testes unitários. A clareza e organização do código, bem como a documentação, serão critérios adicionais de avaliação.


**Observações:**

- O prazo para entrega da prova de conceito é de 10 dias a partir da data de recebimento.
- Os testes devem cobrir pelo menos 80% do código dos serviços.