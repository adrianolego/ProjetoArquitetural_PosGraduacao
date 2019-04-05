package com.adriano.controledefrota.component;

import com.adriano.controledefrota.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AtualizarFrotaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.atualizarVeiculo.exchange}")
    private String atualizarVeiculoExchange;
    @Value("${config.atualizarVeiculo.sendQueue}")
    private String atualizarVeiculoQueue;


    public void atualizarVeiculo(PedidoEncomenda encomenda) {
        rabbitTemplate.convertAndSend(atualizarVeiculoExchange, atualizarVeiculoQueue, encomenda);
    }
}

