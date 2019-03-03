package com.arquitetura.ModuloColeta.resource;

import com.arquitetura.ModuloColeta.model.Encomenda;
import com.arquitetura.ModuloColeta.service.EncomendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encomenda")
public class EncomendaResource {
    @Autowired
    EncomendaService encomendaService;

    @PostMapping
    public void gerarPedido(@RequestBody Encomenda encomenda) {
        encomendaService.gerarPedido();
    }
}
