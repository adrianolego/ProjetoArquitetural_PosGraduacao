package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private EncomendaService encomendaService;

    @RabbitListener(queues = "${config.encomenda.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void salvarPedido(final Encomenda encomenda) throws Exception {
        try {
            encomendaService.salvarPedido(encomenda);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${config.atualizarFrete.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarFrete(final Encomenda encomenda) throws Exception {
        try {

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${config.atualizarVeiculo.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarVeiculo(final Encomenda encomenda) throws Exception {
        try {

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${config.atualizarLogistica.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarLogistica(final Encomenda encomenda) throws Exception {
        try {

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${config.atualizarExpedicao.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarExpedicao(final Encomenda encomenda) throws Exception {
        try {

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
