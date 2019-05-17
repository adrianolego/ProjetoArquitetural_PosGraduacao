package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private CacheService cacheService;

    @Value("${config.redis.enabled}")
    private Boolean cacheEnabled;


    public void salvarPedido(PedidoEncomenda encomenda) {
        Encomenda e = conversionService.convert(encomenda, Encomenda.class);
        encomendaRepository.save(e);

        cacheService.atualizarCache(e.getIdEncomenda());
    }

    @Cacheable(value = "encomenda",
            key = "#idEncomenda",
            condition = "#root.target.getCacheEnabled()",
            unless = "#result != null")
    public Encomenda consultarEncomenda(String idEncomenda) {
        return encomendaRepository.findByIdEncomenda(idEncomenda);
    }


    public Boolean getCacheEnabled() {
        return cacheEnabled;
    }
}
