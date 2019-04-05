package com.adriano.controledefrota.component;

import com.adriano.controledefrota.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnviarLogisticaProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.enviarLogistica.exchange}")
    private String enviarLogisticaExchange;
    @Value("${config.enviarLogistica.sendQueue}")
    private String enviarLogisticaQueue;


    public void enviarLogistica(PedidoEncomenda encomenda) {
        rabbitTemplate.convertAndSend(enviarLogisticaExchange, enviarLogisticaQueue, encomenda);
    }
}
