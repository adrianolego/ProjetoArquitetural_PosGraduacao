package com.adriano.controledefrete.service;

import com.adriano.controledefrete.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FreteConsumerService {

    @RabbitListener(queues = "${config.calcularFrete.sendQueue}")
    public void calcularFrete(PedidoEncomenda pedido) {
        System.out.println(pedido);
    }
}
