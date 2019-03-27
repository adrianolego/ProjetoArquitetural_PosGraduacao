package com.adriano.controledefrota.client;

import com.adriano.controledefrete.model.PedidoEncomenda;
import com.adriano.controledefrota.model.Veiculo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "agendarVeiculoExterno", path = "/agendarVeiculoExterno")
public interface AgendarVeiculoClient {

    @PostMapping()
    Veiculo agendaVeiculo(@RequestBody PedidoEncomenda encomenda);
}
