package com.adriano.integrador.consumer;

import com.adriano.integrador.model.PedidoEncomenda;
import com.adriano.integrador.service.FaturamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FaturamentoConsumer {
    @Autowired
    FaturamentoService faturamentoService;

    @RabbitListener(queues = "${config.registrarFaturamento.sendQueue}")
    public void calcularFrete(PedidoEncomenda pedido) {
        try {

            log.info("Enviando pedido para fila de faturamento.");
            faturamentoService.registrarFaturamento(pedido);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
