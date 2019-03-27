package com.adriano.controledefaturamento.consumer;

import com.adriano.controledefaturamento.model.PedidoEncomenda;
import com.adriano.controledefaturamento.service.FaturamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FaturamentoConsumer {

    @Autowired
    private FaturamentoService faturamentoService;

    @RabbitListener(queues = "${config.registrarFaturamento.sendQueue}")
    public void registrarEncomenda(PedidoEncomenda pedido) {
        try {

            log.info("Registrar pedido de encomenda: {}", pedido);
            faturamentoService.registrarFaturamento(pedido);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
