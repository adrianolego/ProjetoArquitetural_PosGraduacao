package com.adriano.controledefrete.client;

import com.adriano.controledefrete.client.dto.EntradaFreteDTO;
import com.adriano.controledefrete.client.dto.RetornoFreteDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "integrador", path = "/frete", contextId = "frete")
public interface CalcularFreteClient {

    @RequestMapping("/freteExterno")
    RetornoFreteDTO calcularFreteExterno(@RequestBody EntradaFreteDTO entradaFreteDTO);
}
