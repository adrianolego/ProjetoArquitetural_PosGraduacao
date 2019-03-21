package com.adriano.integrador.resource;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "API REST Cálculo de frete externo")
@RestController
@RequestMapping("/freteExterno")
public class FreteResource {
}
