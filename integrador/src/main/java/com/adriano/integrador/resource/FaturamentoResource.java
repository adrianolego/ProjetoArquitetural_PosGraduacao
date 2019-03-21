package com.adriano.integrador.resource;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "API REST Registro de faturamento")
@RestController
@RequestMapping("/faturamento")
public class FaturamentoResource {
}
