package com.adriano.controledefrete.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogisticaService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.logistica.exchange}")
    private String logisticaExchange;
    @Value("${config.logistica.sendQueue}")
    private String logisticaQueue;

    public void realizarLogistica() {
         rabbitTemplate.convertAndSend(logisticaExchange, logisticaQueue, "");
    }
}
