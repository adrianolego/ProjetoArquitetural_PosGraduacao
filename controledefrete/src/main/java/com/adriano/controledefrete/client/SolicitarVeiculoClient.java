package com.adriano.controledefrete.client;

import com.adriano.controledefrete.model.Frete;
import com.adriano.controledefrete.model.Veiculo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="")
public interface SolicitarVeiculoClient {

    @ApiOperation(value = "Solicitação de agendamento de vepiculo")
    @GetMapping
    public Veiculo solicitarVeiculo(@RequestBody Frete frete);
}
