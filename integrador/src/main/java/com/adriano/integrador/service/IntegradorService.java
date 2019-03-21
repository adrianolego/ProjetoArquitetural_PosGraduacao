package com.adriano.integrador.service;

import com.adriano.integrador.model.Frete;
import com.adriano.integrador.model.PedidoEncomenda;
import com.adriano.integrador.model.Veiculo;
import org.springframework.stereotype.Service;

@Service
public class IntegradorService {

    public Frete calcularFrete(PedidoEncomenda encomenda) {
        Frete frete = new Frete();
        if (encomenda != null) {
            frete.setClassificacaoEnvio("");
            frete.setClassificacaoTransporte("");
            frete.setDistanciaKM(100d);
            frete.setExisteCargaRetorno(false);
            frete.setUrgencia("");
        }
        return frete;
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
    public boolean registrarExpedicao(PedidoEncomenda encomenda){
        return encomenda.getExpedicao();
    }
}
