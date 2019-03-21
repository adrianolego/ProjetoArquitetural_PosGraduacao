package com.adriano.controledefrete.service;

import com.adriano.controledefrete.client.CalcularFreteClient;
import com.adriano.controledefrete.client.RegistrarFaturamentoClient;
import com.adriano.controledefrete.client.SolicitarVeiculoClient;
import com.adriano.controledefrete.model.Frete;
import com.adriano.controledefrete.model.PedidoEncomenda;
import com.adriano.controledefrete.model.Veiculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

@Service
@EnableFeignClients
public class FreteConsumerService {

    private final Logger logger = LoggerFactory.getLogger(FreteConsumerService.class);

    @Autowired
    SolicitarVeiculoClient solicitarVeiculoClient;

    @Autowired
    CalcularFreteClient calcularFreteClient;

    @Autowired
    RegistrarFaturamentoClient registrarFaturamentoClient;

    @RabbitListener(queues = "${config.calcularFrete.sendQueue}")
    public void calcularFrete(PedidoEncomenda pedido) {
        try {

            Veiculo veiculo = solicitarVeiculoClient.solicitarVeiculo(pedido.getFrete());

            if (veiculo == null) {
                logger.info("Não foi possível obter veículo");
            }

            if (!registrarFaturamentoClient.registrarFaturamento(pedido)) {
                logger.info("Não foi possível registrar o pedido no faturamento");
            }

            Frete frete = calcularFreteClient.calcularFreteExterno(pedido);

            if (frete == null) {
                logger.info("Não foi possível obter frete externo");
            }

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
