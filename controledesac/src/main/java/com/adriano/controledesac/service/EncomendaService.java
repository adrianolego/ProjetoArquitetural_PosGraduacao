package com.adriano.controledesac.service;

import com.adriano.controledesac.converter.EncomendaConverter;
import com.adriano.controledesac.document.EncomendaDocument;
import com.adriano.controledesac.dto.PedidoEncomendaDTO;
import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncomendaService {

    @Autowired
    EncomendaRepository encomendaRepository;

    @Autowired
    EncomendaConverter encomendaConverter;

    public void salvarPedido(PedidoEncomendaDTO encomenda) {
//        EncomendaDocument dadosEncomenda = encomendaConverter.convert(encomenda);
        EncomendaDocument dadosEncomenda = new EncomendaDocument().setIdEncomenda(encomenda.getIdEncomenda());
        encomendaRepository.save(dadosEncomenda);
    }
}
