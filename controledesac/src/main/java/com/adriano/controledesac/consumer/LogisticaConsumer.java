package com.adriano.controledesac.consumer;

import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.service.LogisticaService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticaConsumer {

    @Autowired
    private LogisticaService logisticaService;

    @RabbitListener(queues = "${config.atualizarLogistica.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarLogistica(PedidoEncomenda encomenda) throws Exception {
        try {
            logisticaService.atualizarLogistica(encomenda);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
