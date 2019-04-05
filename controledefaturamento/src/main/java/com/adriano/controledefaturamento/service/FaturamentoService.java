package com.adriano.controledefaturamento.service;

import com.adriano.controledefaturamento.client.RegistrarFaturamentoClient;
import com.adriano.controledefaturamento.client.dto.EntradaFreteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FaturamentoService {

    @Autowired
    private RegistrarFaturamentoClient registrarFaturamentoClient;

    public boolean registrarFaturamento(EntradaFreteDTO entradaFreteDTO) {
        return registrarFaturamentoClient.registrarFaturamento(entradaFreteDTO);
    }

    public boolean atualizarFaturamento(EntradaFreteDTO entradaFreteDTO) {
        return registrarFaturamentoClient.registrarFaturamento(entradaFreteDTO);
    }

}
