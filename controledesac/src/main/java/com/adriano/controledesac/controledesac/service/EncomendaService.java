package com.adriano.controledesac.controledesac.service;

import com.adriano.controledesac.controledesac.dto.PedidoEncomendaDTO;
import com.adriano.controledesac.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncomendaService {

    @Autowired
    EncomendaRepository encomendaRepository;

    public void salvarPedido(PedidoEncomendaDTO encomenda) {
//        encomendaRepository.save(encomenda);
    }
}
