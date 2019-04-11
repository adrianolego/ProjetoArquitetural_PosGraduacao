package com.adriano.controledelogistica.service;

import com.adriano.controledelogistica.producer.AtualizarLogisticaProducer;
import com.adriano.controledelogistica.producer.EnviarExpedicaoProducer;
import com.adriano.controledelogistica.model.Logistica;
import com.adriano.controledelogistica.model.PedidoEncomenda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class LogisticaService {
    @Autowired
    private EnviarExpedicaoProducer enviarExpedicaoProducer;

    @Autowired
    private AtualizarLogisticaProducer atualizarLogisticaProducer;

    public void gerarLogistica(PedidoEncomenda encomenda) {

        log.info("Gerando logística: {}", encomenda);
        encomenda.setLogistica(Logistica.builder()
                .dataHoraPevisaoSaida(LocalDateTime.now().plusDays(1))
                .descricaocaminho("Siga a BR-050 até Limeira. Pegue a saída 143 via BR-050\n" +
                        "34 min (50,5 km), \n" +
                        "Siga na direção noroeste na BR-050\n" +
                        "10,3 km,\n" +
                        "BR-050 faz uma curva suave à direita e se torna BR-050\n" +
                        " Estrada com pedágio em alguns trechos\n" +
                        "39,7 km,\n" +
                        "Continue em frente para permanecer na BR-050\n" +
                        "150 m,\n" +
                        "Pegue a saída 143 em direção a Limeira/Av. Costa e Silva/Iracemápolis\n" +
                        "350 m, \n" +
                        "Dirija até Via Antônio Cruãnes Filho em Vila Cristovam\n" +
                        "8 min (4,7 km), \n" +
                        "Vire à esquerda em direção à Av. Rod. Anhanguera\n" +
                        "52 m,\n" +
                        "Vire à direita na Av. Rod. Anhanguera\n" +
                        "93 m,\n" +
                        "Na rotatória, pegue a 1ª saída para a Av. Mal. Artur da Costa e Silva\n" +
                        "600 m,\n" +
                        "Na Praça Taba do Brasil, pegue a 5ª saída para a Via Antônio Cruãnes Filho/Av. Mal. Artur da Costa e Silva em direção a Distr. Indl. S. Fumagalli/CETESB/Shopping Nações\n" +
                        " Continue na Via Antônio Cruãnes Filho\n" +
                        "2,1 km,\n" +
                        "Na rotatória, pegue a 1ª saída e mantenha-se na Via Antônio Cruãnes Filho\n" +
                        "1,0 km,\n" +
                        "Na rotatória, pegue a 3ª saída e mantenha-se na Via Antônio Cruãnes Filho em direção a Zona Oeste/Piracicaba\n" +
                        "450 m,\n" +
                        "Na rotatória, pegue a 2ª saída e mantenha-se na Via Antônio Cruãnes Filho em direção a Zona Oeste/Piracicaba")
                .responsavelRota("Fulano da Logistica")
                .build());

        log.info("Enviando expedição: {}", encomenda);
        enviarExpedicaoProducer.enviarExpedicao(encomenda);

        log.info("Atualizando logística: {}", encomenda);
        atualizarLogisticaProducer.atualizarLogistica(encomenda);
    }
}
