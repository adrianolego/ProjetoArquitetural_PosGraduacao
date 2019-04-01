package com.adriano.controledelogistica.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogisticaConsumer {

    @RabbitListener(queues = "${config.atualizarLogistica.sendQueue}")
    public void gerarLogistica( ) {
        try {

            log.info("Logistica: {}","");

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
