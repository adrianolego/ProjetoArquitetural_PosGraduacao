package com.adriano.controledesac.service;

import com.adriano.controledesac.converter.PedidoEncomendaToEncomendaDocumentConverter;
import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

        } else {
            log.info("Pedido não encontrado: {}", encomenda.getIdEncomenda());
        }
    }
}
