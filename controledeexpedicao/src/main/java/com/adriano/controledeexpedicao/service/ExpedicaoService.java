package com.adriano.controledeexpedicao.service;

import com.adriano.controledeexpedicao.client.AtualizarFaturamentoClient;
import com.adriano.controledeexpedicao.client.dto.FaturamentoDTO;
import com.adriano.controledeexpedicao.component.AtualizarExpedicaoProducer;
import com.adriano.controledeexpedicao.model.PedidoEncomenda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ExpedicaoService {

    @Autowired
    AtualizarFaturamentoClient atualizarFaturamentoClient;

    @Autowired
    AtualizarExpedicaoProducer atualizarExpedicaoProducer;

    public PedidoEncomenda gerarExpedicao(PedidoEncomenda pedidoEncomenda) {

        log.info("Gerando expedição: {}", pedidoEncomenda);
        pedidoEncomenda.getExpedicao().builder()
                .dataHoraSaida(LocalDateTime.now())
                .documentoTransporte("")
                .enviado(true)
                .responsavelEnvio("Teste envio")
                .build();

        log.info("Atualizando Faturamento: {}", pedidoEncomenda);
        atualizarFaturamentoClient.atualizarFaturamento(FaturamentoDTO.builder()
                .dataHoraSaida(pedidoEncomenda.getExpedicao().getDataHoraSaida())
                .enviado(pedidoEncomenda.getExpedicao().isEnviado())
                .responsavelEnvio(pedidoEncomenda.getExpedicao().getResponsavelEnvio())
                .build());

        log.info("Atualizando expedição: {}", pedidoEncomenda);
        atualizarExpedicaoProducer.atualizarExpedicao(pedidoEncomenda);

        return pedidoEncomenda;
    }

}
