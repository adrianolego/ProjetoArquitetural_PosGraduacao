package com.adriano.controledesac.service;

import com.adriano.controledesac.converter.PedidoEncomendaToEncomendaDocumentConverter;
import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FreteService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private PedidoEncomendaToEncomendaDocumentConverter converter;

    public void atualizarFrete(PedidoEncomenda encomenda) {
        Optional<Encomenda> atualizarFrete =
                encomendaRepository.findById(encomenda.getIdEncomenda());

        // valida se a encomenda já foi enviada anteriormente e pode estar sendo reenviada
        if (atualizarFrete.isPresent()) {

            Encomenda enc = atualizarFrete.get();

            Encomenda persistencia = converter.convert(encomenda);

            if (persistencia.getFrete() != null) {
                enc.getFrete().addAll(persistencia.getFrete());
            }

            try {
                encomendaRepository.save(atualizarFrete.get());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

        } else {
            log.info("Pedido não encontrado: {}", encomenda.getIdEncomenda());
        }
    }
}
