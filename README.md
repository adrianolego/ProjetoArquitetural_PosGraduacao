# ProjetoArquitetural
Comandos:
 - maven:mvn spring-boot:run                        
 - Mongo: docker run -d --name mongodb -p 27017:27017 mongo       
 docker exec -it mongodb mongo                                  
 docker start mongodb                               
 http://localhost:27017/                         
 https://medium.com/dockerbr/mongodb-no-docker-dd3b72c7efb7
 
- Rabbit:                                    
docker run -d -p 16672:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management

 - Filas do rabbit: http://localhost:16672/#/queues
 - Swagger: http://localhost:8080/swagger-ui.html
 - Docker: https://www.digitalocean.com/community/tutorials/como-instalar-e-usar-o-docker-no-ubuntu-18-04-pt
 - Json encomenda:
{
  "dataHoraRecebimento": "2019-03-20T11:50:15.379Z",
  "destinatario": {
    "bairro": "Centro",
    "celular": "19 99999-9999",
    "cep": "13480-000",
    "cidade": "Limeira",
    "email": "teste@teste.com",
    "logradouro": "R: Tiradentes",
    "nome": "Teste",
    "numero": "123",
    "telefone": ""
  },
  "frete": {
    "classificacaoEnvio": "Normal",
    "classificacaoTransporte": "Granel",
    "distanciaKM": 100,
    "existeCargaRetorno": false,
    "urgencia": "Normal"
  },
  "idEncomenda": "123456789",
  "nomeOperador": "Fulano",
  "observacao": "",
  "remetente": {
    "bairro": "Centro",
    "celular": "19 98888-8888",
    "cep": "13481-000",
    "cidade": "Campinas",
    "email": "teste2@teste.com",
    "logradouro": "Av: Amoreiras",
    "nome": "João de teste",
    "numero": "123",
    "telefone": "19 3444-4444"
  }
}

Json Encomenda-Sac:

{
  "destinatario": {
    "bairro": "Centro",
    "celular": "19 99999-9999",
    "cep": "13480-000",
    "cidade": "Limeira",
    "email": "teste@teste.com",
    "logradouro": "R: Tiradentes",
    "nome": "Teste",
    "numero": "123",
    "telefone": ""
  },
  "frete": {
    "classificacaoEnvio": "Normal",
    "classificacaoTransporte": "Granel",
    "distanciaKM": 100,
    "existeCargaRetorno": false,
    "urgencia": "Normal"
  },
  "observacao": "string",
  "remetente": {
    "bairro": "Centro",
    "celular": "19 98888-8888",
    "cep": "13481-000",
    "cidade": "Campinas",
    "email": "teste2@teste.com",
    "logradouro": "Av: Amoreiras",
    "nome": "João de teste",
    "numero": "123",
    "telefone": "19 3444-4444"
  }
}

