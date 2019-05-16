package com.adriano.controledesac.service;

import com.adriano.controledesac.model.PedidoEncomenda;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

@Service
public class CacheService {

    @Value("${config.redis.enabled}")

    @Cacheable(value = "encomenda", key = "#encomenda.idEncomenda")
    @CachePut
    public void inserirCache(PedidoEncomenda encomenda) {

    }

    @Cacheable(value = "encomenda", key = "#encomenda.idEncomenda")
    @CachePut
    public void atualizarCache(PedidoEncomenda encomenda) {

    }
}
