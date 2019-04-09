package com.adriano.controledelogistica.component;

import com.adriano.controledelogistica.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AtualizarLogisticaProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.atualizarLogistica.exchange}")
    private String atualizarLogisticaExchange;
    @Value("${config.atualizarLogistica.sendQueue}")
    private String atualizarLogisticaQueue;


    public void atualizarLogistica(PedidoEncomenda encomenda) {
        rabbitTemplate.convertAndSend(atualizarLogisticaExchange, atualizarLogisticaQueue, encomenda);
    }
}
