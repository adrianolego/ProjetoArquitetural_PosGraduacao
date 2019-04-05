package com.adriano.controledefaturamento.component;

import com.adriano.controledefaturamento.client.dto.EntradaFreteDTO;
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
    public boolean registrarFaturamento(PedidoEncomenda pedido) {
        EntradaFreteDTO req = EntradaFreteDTO.builder()
                .cepDestino(pedido.getDestinatario().getCep())
                .cepOrigem(pedido.getRemetente().getCep())
                .pesoKg(pedido.getPesoKg())
                .tipoCarga(pedido.getFrete().getTipoCarga())
                .dataColeta(pedido.getFrete().getDataColeta())
                .build();
        try {

            log.info("Registrando faturamento: {}", req);
            return faturamentoService.registrarFaturamento(req);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "${config.atualizarFaturamento.sendQueue}")
    public boolean atualizarFaturamento(PedidoEncomenda pedido) {

        EntradaFreteDTO req = EntradaFreteDTO.builder()
                .cepDestino(pedido.getDestinatario().getCep())
                .cepOrigem(pedido.getRemetente().getCep())
                .pesoKg(pedido.getPesoKg())
                .tipoCarga(pedido.getFrete().getTipoCarga())
                .dataColeta(pedido.getFrete().getDataColeta())
                .build();

        try {

            log.info("Atualizando faturamento: {}", req);
            return faturamentoService.atualizarFaturamento(req);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
