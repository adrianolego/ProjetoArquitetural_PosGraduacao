# ProjetoArquitetural
Comandos:
 - docker-compose up --build  (docker exec -it projetoarquitetural_mongodb_1 mongo)    
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
 
 Módulos e Portas
 Controle de coleta: 8081
 Controle de Frete: 8082
 Controle de Faturamento: 8083
 Controle de Frota: 8084
 Controle de Logística: 8085
 Controle de expedição : 8086
 Controle de Sac: 8087
 Integrador: 7000
 
 - Json encomenda:
{  
   "dataHoraRecebimento":"2019-03-20T11:50:15.379Z",
   "destinatario":{  
      "bairro":"Centro",
      "celular":"19 99999-9999",
      "cep":"13480-001",
      "cidade":"Limeira",
      "email":"teste@teste.com",
      "logradouro":"R: Tiradentes",
      "nome":"Teste",
      "numero":"123",
      "telefone":""
   },
   "frete":{  
      "existeCargaRetorno":true,
      "prioridadeEnvio":"NORMAL",
      "tipoCarga":"GRANEL",
      "pesoKg":"100"
   },
   "nomeOperador":"Fulano",
   "observacao":"",
   "remetente":{  
      "bairro":"Centro",
      "celular":"19 98888-8888",
      "cep":"13481-000",
      "cidade":"Campinas",
      "email":"teste2@teste.com",
      "logradouro":"Av: Amoreiras",
      "nome":"João de teste",
      "numero":"123",
      "telefone":"19 3444-4444"
   }
}


Comando do mongoDB
 - show dbs
 - use controledelogistica
 - db.encomenda.find() (lista documentos)
 - db.encomenda.remove({}) (remove todos documentos)

