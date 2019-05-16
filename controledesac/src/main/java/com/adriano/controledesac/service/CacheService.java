package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private EncomendaRepository encomendaRepository;

//    @Value("${config.redis.enabled}")
//
//    @Cacheable(value = "encomenda", key = "#idEncomenda")
//    @CachePut
    public Encomenda inserirCache(String idEncomenda) {
        return encomendaRepository.findByIdEncomenda(idEncomenda);

    }

//    @Cacheable(value = "encomenda", key = "#idEncomenda")
//    @CachePut
//    public Encomenda atualizarCache(String idEncomenda) {
//        return encomendaRepository.findByIdEncomenda(idEncomenda);
//    }
}
