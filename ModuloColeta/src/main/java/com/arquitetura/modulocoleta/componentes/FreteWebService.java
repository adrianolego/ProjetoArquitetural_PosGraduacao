package com.arquitetura.modulocoleta.componentes;

import com.arquitetura.modulocoleta.model.Encomenda;
import com.arquitetura.modulocoleta.model.Frete;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@Component
public class FreteWebService {

    public Frete buscaFretePorEncomenda(Encomenda dadosEncomenda) {
        RestTemplate template = new RestTemplate();
        return template.getForObject("https://caminho/ws/{cep}/json", Frete.class, dadosEncomenda);
    }
}
