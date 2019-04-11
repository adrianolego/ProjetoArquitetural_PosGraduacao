package com.adriano.controledefrete.service;

import com.adriano.controledefrete.client.CalcularFreteClient;
import com.adriano.controledefrete.client.dto.EntradaFreteDTO;
import com.adriano.controledefrete.client.dto.RetornoFreteDTO;
import com.adriano.controledefrete.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
@Slf4j
public class FreteService {

    @Autowired
    private CalcularFreteClient calcularFreteClient;

    public CalculoFrete calculoFrete(PedidoEncomenda pedidoEncomenda) throws Exception {

        CalculoFrete interno = calcularFreteInterno(pedidoEncomenda);
        CalculoFrete externo = calcularFreteExterno(pedidoEncomenda);

        log.info("Frete interno: {}", interno);
        log.info("Frete externo: {}", externo);

        return (externo.getValor() <= interno.getValor()) ? externo : interno;
    }

    private CalculoFrete calcularFreteExterno(PedidoEncomenda pedidoEncomenda) throws Exception {
        EntradaFreteDTO req = EntradaFreteDTO.builder()
                .cepDestino(pedidoEncomenda.getDestinatario().getCep())
                .cepOrigem(pedidoEncomenda.getRemetente().getCep())
                .pesoKg(pedidoEncomenda.getFrete().getPesoKg())
                .tipoCarga(pedidoEncomenda.getFrete().getTipoCarga())
                .dataColeta(pedidoEncomenda.getFrete().getDataColeta())
                .build();

        RetornoFreteDTO resp = null;

        try {
            resp = calcularFreteClient.calcularFreteExterno(req);
        } catch (Exception e) {
            throw new Exception("Não foi possivel consultar frete externo");
        }
        return CalculoFrete.builder()
                .dataEntregaPrevista(resp.getDataHoraEntregaPrevista().toLocalDate())
                .valor(resp.getValor() * 1.1)
                .interno(Boolean.FALSE)
                .pesoKg(req.getPesoKg())
                .build();
    }

    private CalculoFrete calcularFreteInterno(PedidoEncomenda pedidoEncomenda) {

        Remetente remetente = pedidoEncomenda.getRemetente();
        Destinatario destinatario = pedidoEncomenda.getDestinatario();
        Frete frete = pedidoEncomenda.getFrete();

        Integer cepIni = Integer.parseInt(remetente.getCep().substring(0, 2));
        Integer cepFim = Integer.parseInt(destinatario.getCep().substring(0, 2));


        Double valorFinal = Math.abs(2 * cepFim - cepIni) * 10d;
        Instant dataEntregaPrevista = frete.getDataColeta()
                .atStartOfDay(ZoneId.systemDefault()).toInstant().plus(Duration.ofDays(10));

        switch (frete.getPrioridadeEnvio()) {
            case NORMAL:
                dataEntregaPrevista.minus(Duration.ofDays(2));
                valorFinal *= 1.0;
                break;
            case RAPIDA: {
                dataEntregaPrevista.minus(Duration.ofDays(2));
                valorFinal *= 1.1;
                break;
            }
            case URGENTE: {
                dataEntregaPrevista.minus(Duration.ofDays(5));
                valorFinal *= 1.2;
                break;
            }
        }

        switch (frete.getTipoCarga()) {
            case GRANEL:
                break;
            case LIQUIDA:
                valorFinal *= 1.4;
                break;
            case FRIGORIFICADA:
                valorFinal *= 1.3;
                break;
        }

        return
                CalculoFrete.builder()
                        .dataEntregaPrevista(
                                LocalDateTime.ofInstant(dataEntregaPrevista, ZoneOffset.UTC).toLocalDate()
                        )
                        .valor(valorFinal)
                        .interno(Boolean.TRUE)
                        .pesoKg(frete.getPesoKg())
                        .build();
    }

}
