package com.adriano.controledefaturamento.client;

import com.adriano.controledefaturamento.client.dto.EntradaFreteDTO;
import com.adriano.controledefaturamento.model.PedidoEncomenda;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "integrador", path = "/faturamento")
public interface RegistrarFaturamentoClient {

    @PostMapping()
    boolean registrarFaturamento(@RequestBody EntradaFreteDTO entradaFreteDTO);
}
