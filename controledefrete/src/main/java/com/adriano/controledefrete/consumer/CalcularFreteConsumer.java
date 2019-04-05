package com.adriano.controledefrete.consumer;

import com.adriano.controledefrete.producer.AtualizarCalculoFreteProducer;
import com.adriano.controledefrete.producer.EnviarExpedicaoProducer;
import com.adriano.controledefrete.producer.EnviarFrotaProducer;
import com.adriano.controledefrete.producer.RegistrarFaturamentoProducer;
import com.adriano.controledefrete.model.PedidoEncomenda;
import com.adriano.controledefrete.service.FreteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Slf4j
public class CalcularFreteConsumer {

    @Autowired
    private AtualizarCalculoFreteProducer atualizarCalculoFreteProducer;

    @Autowired
    private FreteService freteService;

    @Autowired
    private RegistrarFaturamentoProducer registrarFaturamento;

    @Autowired
    private EnviarExpedicaoProducer enviarExpedicaoProducer;

    @Autowired
    private EnviarFrotaProducer enviarFrotaProducer;

    @RabbitListener(queues = "${config.calcularFrete.sendQueue}")
    public void calcularFrete(PedidoEncomenda pedido) {
        try {

            log.info("Calculando frete: {}", pedido);
            pedido.getFrete().setDataColeta(LocalDate.now().plusDays(2));
            pedido.setCalculoFrete(freteService.calculoFrete(pedido));
            log.info("Frete calculado: {}", pedido);

            log.info("Enviando atualização do SAC com o frete.");
            atualizarCalculoFreteProducer.atualizarFrete(pedido);

            if (pedido.getCalculoFrete().getInterno()) {
                log.info("Enviando para fila de frota o pedido.");
                enviarFrotaProducer.agendarVeiculo(pedido);
            } else {
                log.info("Frete externo, enviando diretamente para fila de expedição.");
                enviarExpedicaoProducer.enviarExpedicao(pedido);
            }

            log.info("Enviando pedido para fila de faturamento.");
            registrarFaturamento.registrarFaturamento(pedido);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
