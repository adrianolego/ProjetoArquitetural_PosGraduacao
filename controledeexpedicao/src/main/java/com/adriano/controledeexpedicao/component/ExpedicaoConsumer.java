package com.adriano.controledeexpedicao.component;

import com.adriano.controledeexpedicao.client.AtualizarFaturamentoClient;
import com.adriano.controledeexpedicao.client.dto.FaturamentoDTO;
import com.adriano.controledeexpedicao.model.PedidoEncomenda;
import com.adriano.controledeexpedicao.service.ExpedicaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExpedicaoConsumer {
    @Autowired
    ExpedicaoService expedicaoService;

    @RabbitListener(queues = "${config.enviarExpedicao.sendQueue}")
    public void gerarExpedicao(PedidoEncomenda pedido) {
        try {

            log.info("Gerando expedição: {}", pedido);
            expedicaoService.gerarExpedicao(pedido);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
