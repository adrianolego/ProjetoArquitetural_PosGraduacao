package com.adriano.controledesac.consumer;

import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.service.ExpedicaoService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpedicaoConsumer {
    @Autowired
    private ExpedicaoService expedicaoService;

    @RabbitListener(queues = "${config.atualizarExpedicao.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarExpedicao(PedidoEncomenda encomenda) throws Exception {
        try {
            expedicaoService.atualizarExpedicao(encomenda);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
