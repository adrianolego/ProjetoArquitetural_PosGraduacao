package com.adriano.controledefrete.component;

import com.adriano.controledefrete.model.PedidoEncomenda;
import com.adriano.controledefrete.service.FreteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FreteConsumer {

    @Autowired
    private AtualizarCalculoFreteProducer atualizarCalculoFreteProducer;

    @Autowired
    private FreteService freteService;

    @RabbitListener(queues = "${config.calcularFrete.sendQueue}")
    public void calcularFrete(PedidoEncomenda pedido) {
        try {

            log.info("Calculando frete: {}", pedido);
            pedido.setCalculoFrete(freteService.calculoFrete(pedido));
            log.info("Frete calculado: {}", pedido.getCalculoFrete());

            log.info("Enviando atualização do SAC com o frete.");
            atualizarCalculoFreteProducer.atualizarFrete(pedido);

            if (pedido.getCalculoFrete().getInterno()) {
                log.info("Enviando para fila de frota o pedido.");
                atualizarCalculoFreteProducer.atualizarFrete(pedido);
            } else {
                log.info("Frete externo, enviando diretamente para fila de expedição.");
                //TODO send to fila de expedição;
            }

            log.info("Enviando pedido para fila de faturamento.");
            //TODO send to fila de faturamento



        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
