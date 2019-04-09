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
public class LogisticaService {
    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private PedidoEncomendaToEncomendaDocumentConverter converter;

    public void atualizarLogistica(PedidoEncomenda encomenda) {
        Optional<Encomenda> atualizarLogistica =
                encomendaRepository.findById(encomenda.getIdEncomenda());

        // valida se a encomenda já foi enviada anteriormente e pode estar sendo reenviada
        if (atualizarLogistica.isPresent()) {

            Encomenda enc = atualizarLogistica.get();

            Encomenda persistencia = converter.convert(encomenda);

            if (persistencia.getExpedicao() != null) {
                enc.getExpedicao().addAll(persistencia.getExpedicao());
            }

            atualizarLogistica.get().setLogistica(enc.getLogistica());

            try {
                encomendaRepository.save(atualizarLogistica.get());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } else {
            log.info("Pedido não encontrado: {}", encomenda.getIdEncomenda());
        }
    }
}
