package com.adriano.integrador.resource;

import com.adriano.integrador.dto.EntradaFreteDTO;
import com.adriano.integrador.dto.VeiculoDTO;
import com.adriano.integrador.service.FrotaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "API REST Pedido de reserva de veículo")
@RestController
@RequestMapping("/reservarVeiculo")
public class FrotaResource {

    @Autowired
    private FrotaService frotaService;

    @PostMapping()
    public VeiculoDTO agendarVeiculo(@RequestBody EntradaFreteDTO entradaFreteDTO) {
        return frotaService.agendarVeiculo(entradaFreteDTO);
    }
}
