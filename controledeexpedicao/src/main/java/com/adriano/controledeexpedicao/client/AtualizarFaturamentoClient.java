package com.adriano.controledeexpedicao.client;

import com.adriano.controledeexpedicao.client.dto.FaturamentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "atualizarFaturamento", path = "/atualizarFaturamento")
public interface AtualizarFaturamentoClient {

    @PostMapping()
    void atualizarFaturamento(@RequestBody FaturamentoDTO faturamento);
}