package com.adriano.controledelogistica.client;

import com.adriano.controledefrete.model.PedidoEncomenda;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RegistrarFaturamentoClient {

    @GetMapping
    boolean registrarFaturamento(@RequestBody PedidoEncomenda encomenda);
}
