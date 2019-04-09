package com.adriano.controledesac.resource;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.service.EncomendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "API REST Pedidos")
@RestController
@RequestMapping("/pedido")
public class EncomendaResource {

    private final Logger logger = LoggerFactory.getLogger(EncomendaResource.class);

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private EncomendaService encomendaService;

    @ApiOperation(value = "Entrada de pedido de transporte")
    @PostMapping()
    public void registrarPedido(@RequestBody PedidoEncomenda pedidoEncomenda) {
        encomendaService.salvarPedido(pedidoEncomenda);
    }
}
