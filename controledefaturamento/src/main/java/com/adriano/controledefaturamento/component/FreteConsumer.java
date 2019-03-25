package com.adriano.controledefaturamento.component;

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

    @Autowired
    RegistrarFaturamentoProducer registrarFaturamento;

    @Autowired
    EnviarExpedicaoProducer enviarExpedicaoProducer;

    @RabbitListener(queues = "${config.calcularFrete.sendQueue}")
    public void calcularFrete(PedidoEncomenda pedido) {
        try {

            log.info("Calculando frete: {}", pedido);
            pedido.setCalculoFrete(freteService.calculoFrete(pedido));
            log.info("Frete calculado: {}", pedido);

            log.info("Enviando atualização do SAC com o frete.");
            atualizarCalculoFreteProducer.atualizarFrete(pedido);

            if (pedido.getCalculoFrete().getInterno()) {
                log.info("Enviando para fila de frota o pedido.");
                atualizarCalculoFreteProducer.atualizarFrete(pedido);
            } else {
                log.info("Frete externo, enviando diretamente para fila de expedição.");
                enviarExpedicaoProducer.registrarFaturamento(pedido);
            }

            log.info("Enviando pedido para fila de faturamento.");
            registrarFaturamento.registrarFaturamento(pedido);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
