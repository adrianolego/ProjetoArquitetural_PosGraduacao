package com.adriano.controledefaturamento.service;

import com.adriano.controledefaturamento.client.RegistrarFaturamentoClient;
import com.adriano.controledefaturamento.model.PedidoEncomenda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FaturamentoService {

    @Autowired
    private RegistrarFaturamentoClient registrarFaturamentoClient;

    public void registrarFaturamento(PedidoEncomenda pedidoEncomenda) {
        registrarFaturamentoClient.registrarFaturamento(pedidoEncomenda);
    }

}
