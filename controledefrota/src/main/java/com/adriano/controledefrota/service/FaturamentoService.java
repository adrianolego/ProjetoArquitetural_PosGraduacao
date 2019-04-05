package com.adriano.controledefrota.service;

import com.adriano.controledefrota.client.RegistrarFaturamentoClient;
import com.adriano.controledefrota.client.dto.EntradaFreteDTO;
import com.adriano.controledefrota.model.PedidoEncomenda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FaturamentoService {
    @Autowired
    RegistrarFaturamentoClient registrarFaturamentoClient;

    public boolean registrarFaturamento(PedidoEncomenda pedido) throws Exception {

        EntradaFreteDTO req = EntradaFreteDTO.builder()
                .cepDestino(pedido.getDestinatario().getCep())
                .cepOrigem(pedido.getRemetente().getCep())
                .pesoKg(pedido.getPesoKg())
                .tipoCarga(pedido.getFrete().getTipoCarga())
                .dataColeta(pedido.getFrete().getDataColeta())
                .build();

        log.info("Registrando faturamento: {}", req);
        boolean resp = false;
        try {
            resp = registrarFaturamentoClient.registrarFaturamento(req);
        } catch (Exception e) {
            throw new Exception("Não foi possivel obter veículo");
        }

        return resp;
    }
}
