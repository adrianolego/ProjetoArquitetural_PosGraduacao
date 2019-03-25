package com.adriano.controledefrota.component;

import com.adriano.controledefrete.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegistrarFaturamentoProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.registrarFaturamento.exchange}")
    private String registrarFaturamentoExchange;
    @Value("${config.registrarFaturamento.sendQueue}")
    private String registrarFaturamentoQueue;


    public void registrarFaturamento(PedidoEncomenda encomenda) {
        rabbitTemplate.convertAndSend(registrarFaturamentoExchange, registrarFaturamentoQueue, encomenda);
    }
}
