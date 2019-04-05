package com.adriano.controledefrete.producer;

import com.adriano.controledefrete.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnviarFrotaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.agendarVeiculo.exchange}")
    private String agendarVeiculoExchange;
    @Value("${config.agendarVeiculo.sendQueue}")
    private String agendarVeiculoQueue;

    public void agendarVeiculo(PedidoEncomenda encomenda) {
        rabbitTemplate.convertAndSend(agendarVeiculoExchange, agendarVeiculoQueue, encomenda);
    }
}
