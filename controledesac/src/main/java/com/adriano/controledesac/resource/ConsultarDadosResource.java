package com.adriano.controledesac.resource;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.service.EncomendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "API REST Pedidos")
@RestController
@RequestMapping("/dados")
public class ConsultarDadosResource {

    private final Logger logger = LoggerFactory.getLogger(ConsultarDadosResource.class);

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private EncomendaService encomendaService;

    @ApiOperation(value = "Consulta dados de encomenda")
    @GetMapping()
    public Encomenda consultarEncomenda(String idEncomenda) {
        return encomendaService.consultarEncomenda(idEncomenda);
    }
}
