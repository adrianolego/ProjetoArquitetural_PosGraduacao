package com.adriano.controledecoleta.resource;

import com.adriano.controledecoleta.dto.PedidoEncomendaDTO;
import com.adriano.controledecoleta.model.PedidoEncomenda;
import com.adriano.controledecoleta.service.FreteService;
import com.adriano.controledecoleta.service.EncomendaService;
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
    FreteService calcularFreteService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    EncomendaService encomendaService;

    @ApiOperation(value = "Entrada de pedido de transporte")
    @PostMapping()
    public String registrarPedido(@RequestBody PedidoEncomendaDTO encomendaDTO) {
        PedidoEncomenda encomenda =  conversionService.convert(encomendaDTO, PedidoEncomenda.class);
        PedidoEncomenda pedido = calcularFreteService.realizarPedido(encomenda);
        encomendaService.registrarEncomenda(pedido);
        return pedido.getIdEncomenda();
    }
}
