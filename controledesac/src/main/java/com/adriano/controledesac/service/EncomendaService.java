package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    CacheService cacheService;

    public void salvarPedido(PedidoEncomenda encomenda) {
        Encomenda e = conversionService.convert(encomenda, Encomenda.class);
        encomendaRepository.save(e);

        cacheService.inserirCache(e.getIdEncomenda());
    }

    public Encomenda consultarEncomenda(String idEncomenda) {
        return encomendaRepository.findByIdEncomenda(idEncomenda);
    }
}
