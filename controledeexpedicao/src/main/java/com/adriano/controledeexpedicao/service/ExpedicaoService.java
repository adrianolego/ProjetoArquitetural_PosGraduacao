package com.adriano.controledeexpedicao.service;

import com.adriano.controledeexpedicao.client.AtualizarExpedicaoClient;
import com.adriano.controledeexpedicao.client.dto.FaturamentoDTO;
import com.adriano.controledeexpedicao.producer.AtualizarExpedicaoProducer;
import com.adriano.controledeexpedicao.model.Expedicao;
import com.adriano.controledeexpedicao.model.PedidoEncomenda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ExpedicaoService {

    @Autowired
    private AtualizarExpedicaoClient atualizarFaturamentoClient;

    @Autowired
    private AtualizarExpedicaoProducer atualizarExpedicaoProducer;

    public PedidoEncomenda gerarExpedicao(PedidoEncomenda pedidoEncomenda) {

        log.info("Gerando expedição: {}", pedidoEncomenda);
        pedidoEncomenda.setExpedicao(Expedicao.builder()
                .dataHoraSaida(LocalDateTime.now())
                .documentoTransporte("35121268252816000146570010000016161002008470-procCte.xml")
                .enviado(true)
                .responsavelEnvio("Cicrano expedição")
                .build());

        log.info("Atualizando Faturamento: {}", pedidoEncomenda);
        boolean atualizado = false;

        try {
            atualizado = atualizarFaturamentoClient.atualizarFaturamento(FaturamentoDTO.builder()
                    .idEncomenda(pedidoEncomenda.getIdEncomenda())
                    .dataHoraSaida(pedidoEncomenda.getExpedicao().getDataHoraSaida())
                    .enviado(pedidoEncomenda.getExpedicao().isEnviado())
                    .responsavelEnvio(pedidoEncomenda.getExpedicao().getResponsavelEnvio())
                    .build());
        } catch (Exception e) {
            log.info("Não foi possivel atualizar faturamento");
        }

        if (atualizado) {
            log.info("Faturamento Atualizado com sucesso!");
        } else {
            log.info("Não foi possivel atualizar faturamento");
        }

        log.info("Atualizando expedição: {}", pedidoEncomenda);
        atualizarExpedicaoProducer.atualizarExpedicao(pedidoEncomenda);

        return pedidoEncomenda;
    }

}
