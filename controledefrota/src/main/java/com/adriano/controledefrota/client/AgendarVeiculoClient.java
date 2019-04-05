package com.adriano.controledefrota.client;

import com.adriano.controledefrota.client.dto.EntradaFreteDTO;
import com.adriano.controledefrota.client.dto.VeiculoDTO;
import com.adriano.controledefrota.model.PedidoEncomenda;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "integrador")
public interface AgendarVeiculoClient {

    @PostMapping()
    VeiculoDTO agendarVeiculo(@RequestBody EntradaFreteDTO entradaFreteDTO);
}
