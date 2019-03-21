package com.adriano.controledefrete.client;

import com.adriano.controledefrete.model.PedidoEncomenda;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="")
public interface RegistrarFaturamentoClient {

    @ApiOperation(value = "Solicitação de registro de entrada no faturamento")
    @GetMapping
    public boolean registrarFaturamento(@RequestBody PedidoEncomenda encomenda);
}
