package com.adriano.controledefrota.client;

import com.adriano.controledefrota.client.dto.EntradaFreteDTO;
import com.adriano.controledefrota.client.dto.VeiculoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "registrarFaturamento")
public interface RegistrarFaturamentoClient {

    @PostMapping()
    boolean registrarFaturamento(@RequestBody EntradaFreteDTO entrada);
}
