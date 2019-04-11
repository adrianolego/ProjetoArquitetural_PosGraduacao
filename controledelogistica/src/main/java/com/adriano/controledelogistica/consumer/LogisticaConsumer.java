package com.adriano.controledelogistica.consumer;

import com.adriano.controledelogistica.model.PedidoEncomenda;
import com.adriano.controledelogistica.service.LogisticaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogisticaConsumer {

    @Autowired
    private LogisticaService logisticaService;

    @RabbitListener(queues = "${config.enviarLogistica.sendQueue}")
    public void gerarLogistica(PedidoEncomenda encomenda) {
        try {

            log.info("Logistica: {}", "");
            logisticaService.gerarLogistica(encomenda);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
