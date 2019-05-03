package com.adriano.controledefrete.client;

import com.adriano.controledefrete.client.dto.EntradaFreteDTO;
import com.adriano.controledefrete.client.dto.RetornoFreteDTO;
import com.adriano.controledefrete.model.Frete;
import com.adriano.controledefrete.model.PedidoEncomenda;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("integrador.frete")
public interface CalcularFreteClient {

    @RequestMapping("/freteExterno")
    RetornoFreteDTO calcularFreteExterno(@RequestBody EntradaFreteDTO entradaFreteDTO);
}
