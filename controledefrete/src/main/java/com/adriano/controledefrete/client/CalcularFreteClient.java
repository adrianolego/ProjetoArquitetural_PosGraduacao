package com.adriano.controledefrete.client;

import com.adriano.controledefrete.model.Frete;
import com.adriano.controledefrete.model.PedidoEncomenda;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="")
public interface CalcularFreteClient {

    @ApiOperation(value = "Solicitação de calculo de frete")
    @GetMapping
    public Frete calcularFreteExterno(@RequestBody PedidoEncomenda encomenda);
}
