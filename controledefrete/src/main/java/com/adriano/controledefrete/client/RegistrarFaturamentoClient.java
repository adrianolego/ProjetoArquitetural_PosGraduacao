package com.adriano.controledefrete.client;

import com.adriano.controledefrete.client.dto.EntradaFreteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("integrador.faturamento")
public interface RegistrarFaturamentoClient {

    @PostMapping("/registrarFaturamento")
    boolean registrarFaturamento(@RequestBody EntradaFreteDTO entrada);
}
