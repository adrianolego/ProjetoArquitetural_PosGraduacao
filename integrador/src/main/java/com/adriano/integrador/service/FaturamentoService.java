package com.adriano.integrador.service;

import com.adriano.integrador.dto.EntradaFreteDTO;
import com.adriano.integrador.dto.FaturamentoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FaturamentoService {

    public boolean registrarFaturamento(EntradaFreteDTO entradaFreteDTO) {
        log.info("Faturamento registrado com sucesso");
        return entradaFreteDTO != null;
    }

    public boolean atualizarFaturamento(FaturamentoDTO faturamentoDTO) {
        log.info("Faturamento atualizado com sucesso");
        return faturamentoDTO != null;
    }
}
