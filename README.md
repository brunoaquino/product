# product

Dependencias: \
 docker  \
 docker-compose \
 
Para Rodar o projeto:

Na pasta do projeto rodar para subir as aplicações necessárias: docker-compose -f docker_compose_config.yml up -d

Acessar Container docker e criar topicos
   docker exec -it kafka bash

Criar Topico order.created: kafka-topics --create --topic order.created --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
Criar Topico order.paid: kafka-topics --create --topic order.paid --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092

Criar Topico order.paid: /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic order.paid --from-beginning


Verificar Topicos: kafka-topics --list --bootstrap-server localhost:9092
Verificar mensagens na Fila: http://localhost:8081/


Requests:

Login
curl --location 'http://localhost:8080/v1/auth/login' \
--header 'Content-Type: application/json' \
--data '{
  "username": "admin",
  "password": "123456"
}'

Criar produto
curl --location 'http://localhost:8080/v1/produtos/' \
--header 'Content-Type: application/json' \
--header 'Authorization: <Bearer Token>' \
--data '{
  "nome": "produto2",
  "descricao": "asdasd",
  "quantidadeEmEstoque": 50,
  "preco": 5.50,
  "categoria": "CAT1"
}'

Verificar Produto Criado
curl --location 'http://localhost:8080/v1/produtos/2' \
--header 'Content-Type: application/json' \
--header 'Authorization: <Bearer Token>'

Consultar Produto no Elasticsearch
curl --location 'http://localhost:8080/v1/produtos/filtros?nome=produto&categoria=CAT1&precoMin=1&precoMax=3000' \
--header 'Authorization: <Bearer Token>'


Criar Pedido
curl --location 'http://localhost:8080/v1/pedido/' \
--header 'Content-Type: application/json' \
--header 'Authorization: <Bearer Token>' \
--data '{
    "itens": [
        {
            "produto": {
                "id": 2
            },
            "quantidade": 5
        }
    ]
}'

Pagar Pedido
curl --location --request POST 'http://localhost:8080/v1/pedido/pagar/16' \
--header 'Content-Type: application/json' \
--header 'Authorization: <Bearer Token>'

Consultar Top 5 usuários que mais compraram
curl --location 'http://localhost:8080/v1/pedido/buscarTop5UsuariosQueMaisCompraram' \
--header 'Authorization: <Bearer Token>'

Consultar Valor total faturado no mês.
curl --location 'http://localhost:8080/v1/pedido/calcularFaturamentoMensal?ano=2025&mes=2' \
--header 'Authorization: <Bearer Token>'

Consultar Ticket médio dos pedidos de cada usuário.
curl --location 'http://localhost:8080/v1/pedido/calcularTicketMedioPorUsuario' \
--header 'Authorization: <Bearer Token>'
