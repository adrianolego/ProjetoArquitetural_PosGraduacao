package com.adriano.controledefrete.service;

import com.adriano.controledefrete.client.RegistrarFaturamentoClient;
import com.adriano.controledefrete.client.dto.EntradaFreteDTO;
import com.adriano.controledefrete.model.PedidoEncomenda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FaturamentoService {
    @Autowired
    private RegistrarFaturamentoClient registrarFaturamentoClient;

    public boolean registrarFaturamento(PedidoEncomenda pedido) throws Exception {

        EntradaFreteDTO req = EntradaFreteDTO.builder()
                .cepDestino(pedido.getDestinatario().getCep())
                .cepOrigem(pedido.getRemetente().getCep())
                .pesoKg(pedido.getFrete().getPesoKg())
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
