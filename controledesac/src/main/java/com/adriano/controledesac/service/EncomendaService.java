package com.adriano.controledesac.service;

import com.adriano.controledesac.converter.PedidoEncomendaToEncomendaDocumentConverter;
import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;
    @Autowired
    private PedidoEncomendaToEncomendaDocumentConverter converter;

    public void salvarPedido(PedidoEncomenda encomenda) {
        Encomenda e = converter.convert(encomenda);
        encomendaRepository.save(e);
    }

    public Encomenda consultarEncomenda(String idEncomenda) {
        return encomendaRepository.findByIdEncomenda(idEncomenda);
    }
}
