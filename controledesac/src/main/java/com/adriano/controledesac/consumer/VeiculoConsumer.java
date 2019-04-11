package com.adriano.controledesac.consumer;

import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.service.VeiculoService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoConsumer {

    @Autowired
    private VeiculoService veiculoService;

    @RabbitListener(queues = "${config.atualizarVeiculo.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarVeiculo(PedidoEncomenda encomenda) throws Exception {
        try {
            veiculoService.atualizarVeiculo(encomenda);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
