package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Value("${config.redis.enabled}")
    private Boolean cacheEnabled;

    @CachePut(value = "encomenda",
            key = "#idEncomenda",
            condition = "#root.target.getCacheEnabled()",
            unless = "#result != null")
    public Encomenda atualizarCache(String idEncomenda) {
        return encomendaRepository.findByIdEncomenda(idEncomenda);

    }

    public Boolean getCacheEnabled() {
        return cacheEnabled;
    }
}
