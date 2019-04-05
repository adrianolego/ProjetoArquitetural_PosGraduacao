package com.adriano.controledefrota.consumer;

import com.adriano.controledefrota.component.AtualizarFrotaProducer;
import com.adriano.controledefrota.component.EnviarLogisticaProducer;
import com.adriano.controledefrota.model.PedidoEncomenda;
import com.adriano.controledefrota.service.FrotaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FrotaConsumer {
    @Autowired
    FrotaService frotaService;

    @Autowired
    AtualizarFrotaProducer atualizarFrotaProducer;

    @Autowired
    EnviarLogisticaProducer enviarLogisticaProducer;

    @RabbitListener(queues = "${config.agendarVeiculo.sendQueue}")
    public void agendarVeiculo(PedidoEncomenda pedido) {
        try {
            pedido.setVeiculo(frotaService.agendarVeiculo(pedido));

            log.info("Atualizando dados de veículo: {}", pedido);
            atualizarFrotaProducer.atualizarVeiculo(pedido);

            log.info("Enviando dados para logística: {}", pedido);
            enviarLogisticaProducer.enviarLogistica(pedido);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
