package com.adriano.controledefrete.service;

import com.adriano.controledefrete.model.Frete;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AtualizaFreteService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.atualizarFrete.exchange}")
    private String atualizarFreteExchange;
    @Value("${config.atualizarFrete.sendQueue}")
    private String atualizarFreteQueue;


    public void atualizarFrete(Frete frete) {
        rabbitTemplate.convertAndSend(atualizarFreteExchange, atualizarFreteQueue, frete);
    }
}
