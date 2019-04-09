package com.adriano.integrador.resource;

import com.adriano.integrador.dto.EntradaFreteDTO;
import com.adriano.integrador.dto.FaturamentoDTO;
import com.adriano.integrador.service.FaturamentoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "API REST Registro de faturamento")
@RestController
@RequestMapping("/faturamento")
public class FaturamentoResource {

    @Autowired
    private FaturamentoService faturamentoService;

    @PostMapping("/registrarFaturamento")
    public boolean registrarFaturamento(@RequestBody EntradaFreteDTO entradaFreteDTO) {
        return faturamentoService.registrarFaturamento(entradaFreteDTO);
    }


    @PostMapping("/atualizarFaturamento")
    public boolean atualizarFaturamento(@RequestBody FaturamentoDTO faturamentoDTO) {
        return faturamentoService.atualizarFaturamento(faturamentoDTO);
    }
}
