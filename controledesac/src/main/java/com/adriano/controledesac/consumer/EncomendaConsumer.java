package com.adriano.controledesac.consumer;

import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.service.EncomendaService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncomendaConsumer {

    @Autowired
    private EncomendaService encomendaService;

    @RabbitListener(queues = "${config.registrarEncomenda.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void salvarPedido(PedidoEncomenda encomenda) throws Exception {
        try {
            encomendaService.salvarPedido(encomenda);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
