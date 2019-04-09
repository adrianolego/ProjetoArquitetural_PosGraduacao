package com.adriano.controledesac.service;

import com.adriano.controledesac.model.PedidoEncomenda;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private EncomendaService encomendaService;

    @Autowired
    private FreteService freteService;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private LogisticaService logisticaService;

    @Autowired
    private ExpedicaoService expedicaoService;

    @RabbitListener(queues = "${config.registrarEncomenda.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void salvarPedido(final PedidoEncomenda encomenda) throws Exception {
        try {
            encomendaService.salvarPedido(encomenda);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${config.atualizarFrete.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarFrete(final PedidoEncomenda encomenda) throws Exception {
        try {
            freteService.atualizarFrete(encomenda);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${config.atualizarVeiculo.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarVeiculo(final PedidoEncomenda encomenda) throws Exception {
        try {
            veiculoService.atualizarVeiculo(encomenda);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${config.atualizarLogistica.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarLogistica(final PedidoEncomenda encomenda) throws Exception {
        try {
            logisticaService.atualizarLogistica(encomenda);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${config.atualizarExpedicao.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void atualizarExpedicao(final PedidoEncomenda encomenda) throws Exception {
        try {
            expedicaoService.atualizarExpedicao(encomenda);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
