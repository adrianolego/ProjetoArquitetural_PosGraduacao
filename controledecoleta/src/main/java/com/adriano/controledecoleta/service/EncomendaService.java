package com.adriano.controledecoleta.service;

import com.adriano.controledecoleta.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EncomendaService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.registrarEncomenda.exchange}")
    private String pedidoExchange;
    @Value("${config.registrarEncomenda.sendQueue}")
    private String pedidoQueue;

    public void registrarEncomenda(PedidoEncomenda encomenda) {
         rabbitTemplate.convertAndSend(pedidoExchange, pedidoQueue, encomenda);
    }
}
