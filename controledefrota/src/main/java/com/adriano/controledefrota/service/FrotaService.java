package com.adriano.controledefrota.service;

import com.adriano.controledefrota.client.AgendarVeiculoClient;
import com.adriano.controledefrota.client.dto.EntradaFreteDTO;
import com.adriano.controledefrota.client.dto.VeiculoDTO;
import com.adriano.controledefrota.component.AtualizarFrotaProducer;
import com.adriano.controledefrota.component.EnviarLogisticaProducer;
import com.adriano.controledefrota.model.PedidoEncomenda;
import com.adriano.controledefrota.model.Veiculo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FrotaService {

    @Autowired
    AgendarVeiculoClient agendarVeiculoClient;

    public Veiculo agendarVeiculo(PedidoEncomenda pedido) throws Exception {
        EntradaFreteDTO req = EntradaFreteDTO.builder()
                .cepDestino(pedido.getDestinatario().getCep())
                .cepOrigem(pedido.getRemetente().getCep())
                .pesoKg(pedido.getPesoKg())
                .tipoCarga(pedido.getFrete().getTipoCarga())
                .dataColeta(pedido.getFrete().getDataColeta())
                .build();

        log.info("Obtendo veículo: {}", req);
        VeiculoDTO resp = null;
        try {
            resp = agendarVeiculoClient.agendarVeiculo(req);
        } catch (Exception e) {
            throw new Exception("Não foi possivel obter veículo");
        }

        return Veiculo.builder()
                .codigo(resp.getCodigo())
                .pesoMaximoKg(resp.getPesoMaximoKg())
                .placa(resp.getPlaca())
                .quantidadeEixos(resp.getQuantidadeEixos())
                .build();

    }
}
