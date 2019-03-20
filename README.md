# ProjetoArquitetural
Comandos:
 - Mongo: docker run -d --name mongodb -p 27017:27017 mongo       
 docker exec -it mongodb mongo admin          
 docker start mongodb                               
 http://localhost:27017/                         
 https://medium.com/dockerbr/mongodb-no-docker-dd3b72c7efb7
 
- Rabbit
docker -version
docker run -d -p 16672:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management

 - Filas do rabbit: http://localhost:16672/#/queues
 - Swagger: http://localhost:8080/swagger-ui.html
 - Docker: https://www.digitalocean.com/community/tutorials/como-instalar-e-usar-o-docker-no-ubuntu-18-04-pt
 - Json encomenda:
 
 {
  "destinatario": {
    "bairro": "Centro",
    "celular": "19-99999-9999",
    "cep": "13480-000",
    "cidade": "Limeira",
    "email": "teste@teste.com",
    "logradouro": "R Tiradentes",
    "nome": "Adriano",
    "numero": "100",
    "telefone": ""
  },
  "frete": {
    "classificacaoEnvio": "Normal",
    "classificacaoTransporte": "Granel",
    "distanciaKM": 100,
    "existeCargaRetorno": false,
    "urgencia": "Normal"
  },
  "observacao": "",
  "remetente": {
    "bairro": "Centro2",
    "celular": "19-99999-9999",
    "cep": "13480-000",
    "cidade": "Limeira",
    "email": "teste@teste.com",
    "logradouro": "R Tiradentes",
    "nome": "Adriano2",
    "numero": "101",
    "telefone": ""
  }
}
