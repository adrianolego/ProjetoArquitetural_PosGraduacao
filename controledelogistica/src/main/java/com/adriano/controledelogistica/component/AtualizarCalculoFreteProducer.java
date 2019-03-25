package com.adriano.controledelogistica.component;

import com.adriano.controledefrete.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AtualizarCalculoFreteProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.atualizarFrete.exchange}")
    private String atualizarFreteExchange;
    @Value("${config.atualizarFrete.sendQueue}")
    private String atualizarFreteQueue;


    public void atualizarFrete(PedidoEncomenda encomenda) {
        rabbitTemplate.convertAndSend(atualizarFreteExchange, atualizarFreteQueue, encomenda);
    }
}
