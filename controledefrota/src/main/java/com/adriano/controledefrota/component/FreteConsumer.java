package com.adriano.controledefrota.component;

import com.adriano.controledefrota.model.PedidoEncomenda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FreteConsumer {

    @RabbitListener(queues = "${config.calcularFrete.sendQueue}")
    public void calcularFrete(PedidoEncomenda pedido) {
        try {

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
