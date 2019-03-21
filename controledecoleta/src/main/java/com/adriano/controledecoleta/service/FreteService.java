package com.adriano.controledecoleta.service;

import com.adriano.controledecoleta.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FreteService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.calcularFrete.exchange}")
    private String calcularFreteExchange;
    @Value("${config.calcularFrete.sendQueue}")
    private String calcularFreteQueue;

    public PedidoEncomenda realizarPedido(PedidoEncomenda encomenda) {

        encomenda.setIdEncomenda(gerarCodigoRastreio());
        encomenda.setDataHoraRecebimento(LocalDateTime.now());
        rabbitTemplate.convertAndSend(calcularFreteExchange, calcularFreteQueue, encomenda);

        return encomenda;
    }

    private String gerarCodigoRastreio() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
