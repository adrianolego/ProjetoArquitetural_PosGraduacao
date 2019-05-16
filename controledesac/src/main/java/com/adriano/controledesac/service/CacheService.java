package com.adriano.controledesac.service;

import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    @Autowired
    private EncomendaRepository encomendaRepository;

    @Value("${config.redis.enabled}")

    @Cacheable(value = "encomenda", key = "#idEncomenda")
    @CachePut
    public void inserirCache(String idEncomenda) {
        encomendaRepository.findByIdEncomenda(idEncomenda);

    }

    @Cacheable(value = "encomenda", key = "#idEncomenda")
    @CachePut
    public void atualizarCache(String idEncomenda) {
        encomendaRepository.findByIdEncomenda(idEncomenda);
    }
}
