package com.adriano.controledeexpedicao.consumer;

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
    private ExpedicaoService expedicaoService;

    @RabbitListener(queues = "${config.enviarExpedicao.sendQueue}")
    public void gerarExpedicao(PedidoEncomenda pedido) {
        try {

            log.info("Gerando expedição: {}", pedido);
            expedicaoService.gerarExpedicao(pedido);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
