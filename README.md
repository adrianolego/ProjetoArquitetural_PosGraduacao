## Projeto Arquitetural

### Controle de logística

O Projeto conta com 8 módulos desenvolvidos em Spring-boot aos quais interagem usando filas do RabbitMQ, as informações são persistidas por um único módulo em uma base não relacional provida pelo MongoDB, o módulo que realiza a persistência ultiliza uma tecnologia de cache provida pelo Redis para agilizar consultas e minimizar o custo de consultas a base de dados.
Cada módulo recebe sua informação básica faz seu processamento e encaminha uma mensagem na fila para o próximo módulo e outro para o módulo de persistência funcionando assim separadamente e independentes, desde que a mensagem esteja na fila o módulo não depende do anterior ou posterior.


#### Aplicações do Projeto
- Controle de coleta: 8081
- Controle de Frete: 8082
- Controle de Faturamento: 8083
- Controle de Frota: 8084
- Controle de Logística: 8085
- Controle de expedição : 8086
- Controle de Sac: 8087
- Integrador: 7000
- ConfigServer: 8888
- EurekaServer: 8761


#### Controle de coleta:
Módulo resposável pelo recebimento de pedidos de transporte, recebe as informações mínimas do objeto a ser transportado, faz o processamento, encaminha as informações nas filas do módulo de sac para persistência e para o módulo de frete.

#### Controle de Frete:
Módulo resposável pelo cálculo do valor de frete, este módulo faz o cálculo interno e um cálculo externo simulando uma integração com sistema de terceiros, esta comunicação é feita com o Integrador que responde a essas solicitações. Depois de terminado o processamento encaminha as informações nas filas do módulo de sac, outra para o módulo de faturamento e outra para o sistema de frotas.

#### Controle de Faturamento:
Módulo resposável pelo recebimento dos pedido de transporte e que irá gerar uma prestação de serviço e cobrança pelo mesmo, este módulo registra a entrada do pedido e faz os tramites necessários para o recebimento da prestação do serviço.
O módulo se comunica com um sistema em nuvem, este sistema em nuvem é simulado pelo Integrador, e envia as informações para seguir com a geração do faturamento.

#### Controle de Frota:
Módulo resposável pelo agendamento de veículos para transporte tendo como base o tipo de carga e a necessidade, este módulo se comunica com um outro sistema em nuvem que devolve os dados do veículo disponível e adequado a carga e sua data de saída, este sistema em nuvem é simulado pelo Integrador, encaminha as informações nas filas do módulo de sac para persistência e para o módulo de logística.

#### Controle de Logística:
Módulo resposável por gerar a logística de entrega do produto, ele ultiliza as informações que o módulo de controle de frota disponibiliza e cria todo o caminho até a entrega da encomenda, encaminha as informações nas filas do módulo de sac para persistência e para o módulo de expedição.

#### Controle de expedição:
Módulo resposável por despachar a encomenda para seu destino, após feito todos os passos de despacho o sistema informa o controle de faturamento que a encomenda foi enviada e encaminha as informações na fila do módulo de sac para persistência.

#### Controle de Sac:
Módulo resposável por persistir todos os dados gerados pelos demais módulos permitindo assim a consulta em um único ponto que detêm os dados mais atualizados da tramitação de uma encomenda dentro de todos os módulos.

#### Integrador:
Sistema auxiliar criado para simular chamadas a sistemas externos garantindo uma forma de comunicação com outras aplicações.

#### ConfigServer:
Sistema auxiliar que concentra e disponibiliza os arquivos de configurações em um único lugar para facilitar a manutenção. Quando um módulo for ser executado ele pede a esse módulo o arquivo de configuração que ele irá ultilizar.

#### EurekaServer:
Sistema auxiliar que registra todos os módulos que estiverem com seu funcionamento normal, se estiver em execução o módulo é registrado e esse registro servirá para que os módulos possam se localizar dentro do ambiente.


### Configurações do projeto

Para execução do projeto a versão 8 do java deve estar disponível.

No linux configurar o arquivos HOSTS no caminho `/etc/hosts` acrescentando alinha abaixo `127.0.0.1       keycloak` (Windows `C: » Windows » System32 » Drivers » etc`).
Esta configuração serve para que ao rodar o keycloak através de container as aplicações consigam fazer a autenticação.

Necessário ter instalado docker e docker-compose.

Necessário acessar o keycloak e criar um usuário dentro do realm `Logistica` para poder obter token de acesso seguindo os passos abaixo.

- Depois de subir os módulos pelo docker compose (Acessar a pasta do projeto onde se encontra o arquivo `docker-compose.yml` e executar o comando `mvn clean install -DskipTests; docker-compose up --build`).
- Acessar o link `http://localhost:8080/auth/`.
- Usuário e senha `admin`
- Na lista seleciona `Users` depois clique em `Add User`.
- Preencha o nome e clique em `Save`.
- Na tela de `Details` navegue até a aba de `Credencials` preencha a senha e clique em temporary deixando `OFF` e depois `reset password` (Esse usuário e senha que será usado para obter o token de acesso).
- Depois em `Client-Roles` escolha `Logistica` em `Avalible Roles` selecione as permissões e clique em `Add Selected` (Adicionando permissões ao Realm (Reino) onde o usuário vai acessar para obter o token).

Com o docker compose rodando podemos obter o token e iniciar a execução do sistema (Acessar a pasta do projeto onde se encontra o arquivo `docker-compose.yml` e executar o comando `mvn clean install -DskipTests;  docker-compose up --build`).

#### Os seguintes endereços devem estar disponíveis:
- Filas do rabbit: `http://localhost:16672/#/queues` - Usuário e senha `guest`.
- WebService do controle de coleta: `http://localhost:8081/swagger-ui.html` (Entrada de pedidos).
- WebService do controle de sac: `http://localhost:8087/swagger-ui.html` (Consulta de pedidos pelo código de rastreio  gerado no controle de coleta).
- Eureka Server: `http://localhost:8761/` (Lista com todos os módulos em funcionamento e registrado).
- Config Server: `http://localhost:8888/<MODULO>/default` (no lugar do <MODULO>  coloque o nome do módulo, encomenda, frete, logistica, etc, o json de configuração do módulo deve ser exibido). Troque `default` por `compose-profile` para ver o json do compose.
- para obter token de acesso basta executar o seguinte comando no terminal: `curl -X POST http://keycloak:8080/auth/realms/logistica/protocol/openid-connect/token -d "grant_type=password&username=<USER>&password=<SENHA>&client_id=logisticaauth"` trocando `<USER>` e ``<SENHA>` pelo usuário criado para obtenção do token de acesso.
- Depois de executado o comando produziu o seguinte resultado:
{"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ6emRHcm9XaldFWVhvblZRYm5ZMmVacjlXb1dlVTBESFVwSE9uZzRORGxJIn0.eyJqdGkiOiJlNDg5MDdkYS1lZjE3LTQ2NmEtYmZjZS03NTI0MGY1MWMyMmYiLCJleHAiOjE1NTg5NjEzODYsIm5iZiI6MCwiaWF0IjoxNTU4OTYxMDg2LCJpc3MiOiJodHRwOi8va2V5Y2xvYWs6ODA4MC9hdXRoL3JlYWxtcy9sb2dpc3RpY2EiLCJhdWQiOiJsb2dpc3RpY2EiLCJzdWIiOiJiNDJiMGZmMy1lNzk1LTQxODctYTBlMy1hNjA2OWVmNTZmMGYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJsb2dpc3RpY2FhdXRoIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiMjBkMGM0YjItNjMzYy00MjhkLTg5ZGYtZGFjMDQwNWFhZjQzIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsibG9naXN0aWNhIjp7InJvbGVzIjpbImFkbWluIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0ZXN0ZSJ9.WIXF6BqVOg4jpBP8MUR3lJyJZQYL4aKFSOWEHVGgwKSFqyeo7k39M3fNFfN0CxuznVV_M6m9tYqcKOAg_56j_8bezqZUi1U3duCLeDe_O7T7arNi0FjuZNoL_0AgYJORAS4HtzWeWSd0nIP2lR-8CEyf7aR9WIkwtc_GSgpmnVt9B2-fSQrxT5l7y6RCEbQwza92COH_wzAO5obKgoaoxRxroQauU0Z_Mh8oGChq731BP1qJ2AawTamvjJge7-Fq3WBuCvBwK8AsjzJLy2asB0APP7Wc1LFeL5KuQ6PM6xVHBI3CChfcPja9u61HqW7t6h8Ni5Lh_PDG4X6Q0Cx1mA","expires_in":300,"refresh_expires_in":1800,"refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI3MjAwNWI5ZC0zYjM4LTQ4NzItYTgyZi1lMTZiM2NiZjIzNDIifQ.eyJqdGkiOiJkMjM2ZjkyMC0xODBiLTQxNjAtYTFhNC01NjNlNGNkODRhYmYiLCJleHAiOjE1NTg5NjI4ODYsIm5iZiI6MCwiaWF0IjoxNTU4OTYxMDg2LCJpc3MiOiJodHRwOi8va2V5Y2xvYWs6ODA4MC9hdXRoL3JlYWxtcy9sb2dpc3RpY2EiLCJhdWQiOiJodHRwOi8va2V5Y2xvYWs6ODA4MC9hdXRoL3JlYWxtcy9sb2dpc3RpY2EiLCJzdWIiOiJiNDJiMGZmMy1lNzk1LTQxODctYTBlMy1hNjA2OWVmNTZmMGYiLCJ0eXAiOiJSZWZyZXNoIiwiYXpwIjoibG9naXN0aWNhYXV0aCIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6IjIwZDBjNGIyLTYzM2MtNDI4ZC04OWRmLWRhYzA0MDVhYWY0MyIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsibG9naXN0aWNhIjp7InJvbGVzIjpbImFkbWluIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIn0.ITz1NBTYHT-TftnKggHitN8TR8rGRXVgqnhR2TQx6TA","token_type":"bearer","not-before-policy":0,"session_state":"20d0c4b2-633c-428d-89df-dac0405aaf43","scope":"profile email"}adriano@adriano-PC:~/gitAdriano/ProjetoArquitetural$

#### O token fica nessa área
{"access_token":"`eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ6emRHcm9XaldFWVhvblZRYm5ZMmVacjlXb1dlVTBESFVwSE9uZzRORGxJIn0.eyJqdGkiOiJlNDg5MDdkYS1lZjE3LTQ2NmEtYmZjZS03NTI0MGY1MWMyMmYiLCJleHAiOjE1NTg5NjEzODYsIm5iZiI6MCwiaWF0IjoxNTU4OTYxMDg2LCJpc3MiOiJodHRwOi8va2V5Y2xvYWs6ODA4MC9hdXRoL3JlYWxtcy9sb2dpc3RpY2EiLCJhdWQiOiJsb2dpc3RpY2EiLCJzdWIiOiJiNDJiMGZmMy1lNzk1LTQxODctYTBlMy1hNjA2OWVmNTZmMGYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJsb2dpc3RpY2FhdXRoIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiMjBkMGM0YjItNjMzYy00MjhkLTg5ZGYtZGFjMDQwNWFhZjQzIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsibG9naXN0aWNhIjp7InJvbGVzIjpbImFkbWluIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0ZXN0ZSJ9.WIXF6BqVOg4jpBP8MUR3lJyJZQYL4aKFSOWEHVGgwKSFqyeo7k39M3fNFfN0CxuznVV_M6m9tYqcKOAg_56j_8bezqZUi1U3duCLeDe_O7T7arNi0FjuZNoL_0AgYJORAS4HtzWeWSd0nIP2lR-8CEyf7aR9WIkwtc_GSgpmnVt9B2-fSQrxT5l7y6RCEbQwza92COH_wzAO5obKgoaoxRxroQauU0Z_Mh8oGChq731BP1qJ2AawTamvjJge7-Fq3WBuCvBwK8AsjzJLy2asB0APP7Wc1LFeL5KuQ6PM6xVHBI3CChfcPja9u61HqW7t6h8Ni5Lh_PDG4X6Q0Cx1mA`","expires_in":300,"refresh_expires_in":1800,"refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI3MjAwNWI5ZC0zYjM4LTQ4NzItYTgyZi1lMTZiM2NiZjIzNDIifQ.eyJqdGkiOiJkMjM2ZjkyMC0xODBiLTQxNjAtYTFhNC01NjNlNGNkODRhYmYiLCJleHAiOjE1NTg5NjI4ODYsIm5iZiI6MCwiaWF0IjoxNTU4OTYxMDg2LCJpc3MiOiJodHRwOi8va2V5Y2xvYWs6ODA4MC9hdXRoL3JlYWxtcy9sb2dpc3RpY2EiLCJhdWQiOiJodHRwOi8va2V5Y2xvYWs6ODA4MC9hdXRoL3JlYWxtcy9sb2dpc3RpY2EiLCJzdWIiOiJiNDJiMGZmMy1lNzk1LTQxODctYTBlMy1hNjA2OWVmNTZmMGYiLCJ0eXAiOiJSZWZyZXNoIiwiYXpwIjoibG9naXN0aWNhYXV0aCIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6IjIwZDBjNGIyLTYzM2MtNDI4ZC04OWRmLWRhYzA0MDVhYWY0MyIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsibG9naXN0aWNhIjp7InJvbGVzIjpbImFkbWluIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIn0.ITz1NBTYHT-TftnKggHitN8TR8rGRXVgqnhR2TQx6TA","token_type":"bearer","not-before-policy":0,"session_state":"20d0c4b2-633c-428d-89df-dac0405aaf43","scope":"profile email"}adriano@adriano-PC:~/gitAdriano/ProjetoArquitetural$

- Agora com o token vamos acessar a aplicação : http://localhost:8081/swagger-ui.html#/encomenda-resource (Web Service resposável pela entrada de encomendas)
- Procure pelo botão `Authorize` no canto superior direito e clique nele, na tela que ira se abrir no campo `value` digite `Bearer ` e depois cole o token copiado da área mostrada anteriormente e clique em `Authorize`.
- Agora em `encomenda-resource` clique em `Try it out`
- Abaixo contém um exemplo do preenchimento de um json com os dados necessários para que o sistema faça a entrada a encomenda no sistema e retorne o código de rastreio, para testar basta copiar e colar o json no campo com o json de exemplo no link que acessou e clicar em `Execute`

### Json exemplo de encomenda:
`{
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
}`

- Se tudo ocorreu corretamente a aplicação retornará um código de rastreio com esse código podemos consultar se a aplicação fez todos os passos necessários e persistiu as informações na base de dados.
- Consulta do código de rastreio, acessar http://localhost:8087/swagger-ui.html#/consultar-dados-resource clique em `consultar-dados-resource` agora clique em `Try it out` e cole o código da encomenda no campo `idEncomenda` agora clique em execute.
- Um json parecido com o mostrado abaixo deve ser mostrado.

### Exemplo json retorno
      {
        "idEncomenda": "3b0800b1-9653-4ed5-b0e8-2f353b547f9a",
        "frete": [
          {
            "prioridadeEnvio": "NORMAL",
            "tipoCarga": "GRANEL",
            "existeCargaRetorno": true,
            "dataColeta": null,
            "pesoKg": 100
          }
        ],
        "calculoFrete": [
          {
            "valor": 130,
            "dataEntregaPrevista": "2019-06-08",
            "interno": true,
            "pesoKg": 100
          }
        ],
        "remetente": {
          "nome": "João de teste",
          "logradouro": "Av: Amoreiras",
          "numero": "123",
          "bairro": "Centro",
          "cidade": "Campinas",
          "cep": "13481-000",
          "telefone": "19 3444-4444",
          "celular": "19 98888-8888",
          "email": "teste2@teste.com"
        },
        "destinatario": {
          "nome": "Teste",
          "logradouro": "R: Tiradentes",
          "numero": "123",
          "bairro": "Centro",
          "cidade": "Limeira",
          "cep": "13480-001",
          "telefone": "",
          "celular": "19 99999-9999",
          "email": "teste@teste.com"
        },
        "dataHoraRecebimento": "2019-05-27T10:01:59.296",
        "nomeOperador": [
          "Fulano"
        ],
        "observacao": null,
        "logistica": [
          {
            "dataHoraPevisaoSaida": "2019-05-28T10:02:01.428",
            "descricaocaminho": "Siga a BR-050 até Limeira. Pegue a saída 143 via BR-050\n34 min (50,5 km), \nSiga na direção noroeste na BR-050\n10,3 km,\nBR-050 faz uma curva suave à direita e se torna BR-050\n Estrada com pedágio em alguns trechos\n39,7 km,\nContinue em frente para permanecer na BR-050\n150 m,\nPegue a saída 143 em direção a Limeira/Av. Costa e Silva/Iracemápolis\n350 m, \nDirija até Via Antônio Cruãnes Filho em Vila Cristovam\n8 min (4,7 km), \nVire à esquerda em direção à Av. Rod. Anhanguera\n52 m,\nVire à direita na Av. Rod. Anhanguera\n93 m,\nNa rotatória, pegue a 1ª saída para a Av. Mal. Artur da Costa e Silva\n600 m,\nNa Praça Taba do Brasil, pegue a 5ª saída para a Via Antônio Cruãnes Filho/Av. Mal. Artur da Costa e Silva em direção a Distr. Indl. S. Fumagalli/CETESB/Shopping Nações\n Continue na Via Antônio Cruãnes Filho\n2,1 km,\nNa rotatória, pegue a 1ª saída e mantenha-se na Via Antônio Cruãnes Filho\n1,0 km,\nNa rotatória, pegue a 3ª saída e mantenha-se na Via Antônio Cruãnes Filho em direção a Zona Oeste/Piracicaba\n450 m,\nNa rotatória, pegue a 2ª saída e mantenha-se na Via Antônio Cruãnes Filho em direção a Zona Oeste/Piracicaba",
            "responsavelRota": "Fulano da Logistica"
          }
        ],
        "expedicao": [
          {
            "dataHoraSaida": "2019-05-27T10:02:01.522",
            "documentoTransporte": "35121268252816000146570010000016161002008470-procCte.xml",
            "enviado": true,
            "responsavelEnvio": "Cicrano expedição"
          }
        ],
        "veiculo": [
          {
            "codigo": "CARR-CDJ-2154",
            "descricao": null,
            "placa": "CJD-2154",
            "pesoMaximoKg": 1000,
            "quantidadeEixos": 8
          }
        ]
      }

- Sabemos que todos os módulos funcionaram corretamente se os objetos no json não estiverem vazios ou nulos : frete, calculoFrete, remetente, destinatario, logistica, expedicao ou veiculo.


### Possíveis problemas

- Erro ao obter token : Usuário e Senha diferente do criado para autenticação, falta de permissões no Realm `Logistica`
- Encomenda Resource não autorizado: execute o comando novamente para obter o token pois o mesmo pode ter expirado, ao autorizar o acesso colocar Bearer + espaço + token.
- Ao rodar comando para obter token o serviço não esta sendo encontrado: Confira o mapeamento feito no arquivo `hosts` conforme explicano no inicio deste documento.
- Ao consultar o código de rastreio algum/ns objeto/s esta/ão nulo/s: Pode ter ocorrido algum problema na subida dos módulos consulte o Eureka Server e confira se todos subiram corretamente (http://localhost:8761/ deve contar os módulos: Coleta, Expedicao, Faturamento, Frete, Frota, Integrador, Logistica e Sac). Nas filas não pode ter mensagem em nenhuma fila, se tiver mensagem em alguma fila que termine com `.sendDLQQueue` porque algum erro ocorreu e a mensagem não pode ser lida, se a mensagem esta em alguma fica que termine com `sendQueue` é porque o módulo pode não estar rodando portanto a mensagem não foi consumida.
- Alguma das aplicações não subiu: Verificar se as portas ultilizadas pelas aplicações esta em uso onde foram executadas causando conflito de portas.
- Projeto não compilando ou ocorrendo erro na compilação (mvn clean install -DskipTests): verificar versão do java `java --version` precisa ser 1.8.



## Comandos para manutenção:

 -Executar um projeto: `maven:mvn spring-boot:run`. (Rodar as dependência como rabbitMQ, Redis, etc)

## RabbitMQ:
`docker run -d -p 16672:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management`

## Comando do mongoDB
- Mongo: `docker run -d --name mongodb -p 27017:27017 mongo` (Conectar no mongo dentro do container (`docker exec -it projetoarquitetural_mongodb_1 mongo` no compose ou `docker exec -it mongodb mongo` rodando manualmente))
 - `show dbs` (Lista bases disponíveis)
 - `use controledelogistica` (base do projeto deve ser criada após primeiro uso)
 - `db.encomenda.find()` (lista documentos)
 - `db.encomenda.remove({})` (remove todos documentos)

## KeyCloak
`docker run -d -p 8080:8080 keycloak`

## Configuração para Debug

 No docker compose acrescentar `-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005` a linha ficará assim : `JAVA_OPTS=-Xmx256m -Xms128m -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Dspring.profiles.active=docker-compose`

 Depois na IDE criar uma configuração remota usando a porta indicada na configuração acima.
 
## Limpar imagens <none> no docker
  `docker rmi $(docker images -q -f "dangling=true")`
