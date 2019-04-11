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
public class FreteService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PedidoEncomendaToEncomendaDocumentConverter converter;

    public void atualizarFrete(PedidoEncomenda encomenda) {
        Encomenda enc = encomendaRepository.findByIdEncomenda(encomenda.getIdEncomenda());

        // valida se a encomenda já foi enviada anteriormente e pode estar sendo reenviada
        if (enc != null) {

            Encomenda persistencia = converter.convert(encomenda);

            if (persistencia.getCalculoFrete() != null) {
                if (enc.getCalculoFrete().get(0) != null) {
                    enc.getCalculoFrete().addAll(persistencia.getCalculoFrete());
                } else {
                    enc.setCalculoFrete(persistencia.getCalculoFrete());
                }
            }
            encomendaRepository.save(enc);

        } else {
            log.info("Pedido não encontrado: {}", encomenda.getIdEncomenda());
        }
    }
}
