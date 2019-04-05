package com.adriano.integrador.service;

import com.adriano.integrador.dto.EntradaFreteDTO;
import com.adriano.integrador.dto.VeiculoDTO;
import org.springframework.stereotype.Service;

@Service
public class FrotaService {
    public VeiculoDTO agendarVeiculo(EntradaFreteDTO entradaFrete) {
        VeiculoDTO veiculo = new VeiculoDTO();
        if (entradaFrete != null) {
            veiculo.setCodigo("CARR-CDJ-2154");
            veiculo.setDescricao("Carreta");
            veiculo.setPesoMaximoKg(1000d);
            veiculo.setPlaca("CJD-2154");
            veiculo.setQuantidadeEixos(8);
        }
        return veiculo;
    }
}
