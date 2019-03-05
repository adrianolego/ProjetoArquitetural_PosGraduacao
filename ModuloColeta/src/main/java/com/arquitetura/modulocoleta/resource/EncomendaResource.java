package com.arquitetura.modulocoleta.resource;

import com.arquitetura.modulocoleta.model.Encomenda;
import com.arquitetura.modulocoleta.service.EncomendaService;
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
