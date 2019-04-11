package com.adriano.controledesac.consumer;

import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.service.FreteService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreteConsumer {

    @Autowired
    private FreteService freteService;

    @RabbitListener(queues = "${config.atualizarFrete.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarFrete(PedidoEncomenda encomenda) throws Exception {
        try {
            freteService.atualizarFrete(encomenda);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
