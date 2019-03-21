package com.adriano.integrador.resource;

import com.adriano.integrador.dto.EntradaFreteDTO;
import com.adriano.integrador.dto.RetornoFreteDTO;
import com.adriano.integrador.service.IntegradorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "API REST Cálculo de frete externo")
@RestController
@RequestMapping("/freteExterno")
public class FreteResource {

    @Autowired
    private IntegradorService integradorService;

    @PostMapping()
    public RetornoFreteDTO calcularFrete(@RequestBody EntradaFreteDTO entradaFreteDTO) {
        return integradorService.calcularFrete(entradaFreteDTO);
    }

}