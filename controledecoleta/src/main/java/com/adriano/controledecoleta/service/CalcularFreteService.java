package com.adriano.controledecoleta.service;

import com.adriano.controledecoleta.dto.PedidoEncomendaDTO;
import com.adriano.controledecoleta.model.PedidoEncomenda;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CalcularFreteService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.frete.exchange}")
    private String calcularFreteExchange;
    @Value("${config.frete.sendQueue}")
    private String calcularFreteQueue;

    public String realizarPedido(PedidoEncomendaDTO encomenda) {

        String codRastreio = gerarCodigoRastreio();

        PedidoEncomenda pedidoEncomenda = new PedidoEncomenda();
        pedidoEncomenda.setIdEncomenda(codRastreio);
        pedidoEncomenda.setObservacao("teste de envio para fila");
        rabbitTemplate.convertAndSend(calcularFreteExchange, calcularFreteQueue, encomenda);

        return codRastreio;

    }

    private String gerarCodigoRastreio() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
