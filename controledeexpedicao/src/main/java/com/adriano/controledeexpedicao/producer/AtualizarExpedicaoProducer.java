package com.adriano.controledeexpedicao.producer;

import com.adriano.controledeexpedicao.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AtualizarExpedicaoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.atualizarExpedicao.exchange}")
    private String atualizarExpedicaoExchange;
    @Value("${config.atualizarExpedicao.sendQueue}")
    private String atualizarExpedicaoQueue;


    public void atualizarExpedicao(PedidoEncomenda encomenda) {
        rabbitTemplate.convertAndSend(atualizarExpedicaoExchange, atualizarExpedicaoQueue, encomenda);
    }
}
