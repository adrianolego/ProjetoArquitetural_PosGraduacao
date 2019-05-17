package com.adriano.controledesac.service;

import com.adriano.controledesac.converter.PedidoEncomendaToEncomendaDocumentConverter;
import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VeiculoService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PedidoEncomendaToEncomendaDocumentConverter converter;

    @Autowired
    private CacheService cacheService;

    @Value("${config.redis.enabled}")
    private Boolean cacheEnabled;

    public void atualizarVeiculo(PedidoEncomenda encomenda) {

        Encomenda enc = encomendaRepository.findByIdEncomenda(encomenda.getIdEncomenda());

        // valida se a encomenda já foi enviada anteriormente e pode estar sendo reenviada
        if (enc != null) {

            Encomenda persistencia = converter.convert(encomenda);

            if (persistencia.getVeiculo() != null) {
                if (enc.getVeiculo().get(0) != null) {
                    enc.getVeiculo().addAll(persistencia.getVeiculo());
                } else {
                    enc.setVeiculo(persistencia.getVeiculo());
                }
            }

            encomendaRepository.save(enc);

            cacheService.atualizarCache(encomenda.getIdEncomenda());

        } else {
            log.info("Pedido não encontrado: {}", encomenda.getIdEncomenda());
        }
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
