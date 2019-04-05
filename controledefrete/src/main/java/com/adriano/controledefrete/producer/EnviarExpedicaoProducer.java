package com.adriano.controledefrete.producer;

import com.adriano.controledefrete.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnviarExpedicaoProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.enviarExpedicao.exchange}")
    private String enviarExpedicaoExchange;
    @Value("${config.enviarExpedicao.sendQueue}")
    private String enviarExpedicaoQueue;

    public void enviarExpedicao(PedidoEncomenda encomenda) {
        rabbitTemplate.convertAndSend(enviarExpedicaoExchange, enviarExpedicaoQueue, encomenda);
    }
}
