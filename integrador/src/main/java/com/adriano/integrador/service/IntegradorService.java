package com.adriano.integrador.service;

import com.adriano.integrador.dto.EntradaFreteDTO;
import com.adriano.integrador.dto.RetornoFreteDTO;
import com.adriano.integrador.model.Frete;
import com.adriano.integrador.model.PedidoEncomenda;
import com.adriano.integrador.model.Veiculo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
@Slf4j
public class IntegradorService {

    public RetornoFreteDTO calcularFrete(EntradaFreteDTO frete) {
        log.info("Recebendo chamado de calculo de frete: {}", frete);

        Integer cepIni = Integer.parseInt(frete.getCepOrigem().substring(0, 2));
        Integer cepFim = Integer.parseInt(frete.getCepDestino().substring(0, 2));


        Double valorFinal = Math.abs(cepFim - cepIni) * 10d;
        Instant dataEntregaPrevista = frete.getDataColeta()
                .atStartOfDay(ZoneId.systemDefault()).toInstant().plus(Duration.ofDays(10));


        switch (frete.getTipoCarga()) {
            case GRANEL:
                break;
            case LIQUIDA:
                valorFinal *= 1.05;
                break;
            case FRIGORIFICADA:
                valorFinal *= 2;
                break;
        }

        return RetornoFreteDTO.builder()
                .dataHoraEntregaPrevista(
                        LocalDateTime.ofInstant(dataEntregaPrevista, ZoneOffset.UTC)
                )
                .valor(valorFinal)
                .build();
    }

    public Veiculo agendarVeiculo(PedidoEncomenda encomenda) {
        Veiculo veiculo = new Veiculo();
        if (encomenda != null) {
            veiculo.setCodigo("CARR-CDJ-2154");
            veiculo.setDescricao("Carreta");
            veiculo.setPesoMaximoKg(1000d);
            veiculo.setPlaca("CJD-2154");
            veiculo.setQuantidadeEixos(8);
        }
        return veiculo;
    }

    public boolean registrarPedido(PedidoEncomenda encomenda) {
        return encomenda == null;
    }

    public boolean registrarExpedicao(PedidoEncomenda encomenda) {
        return encomenda.getExpedicao();
    }
}
