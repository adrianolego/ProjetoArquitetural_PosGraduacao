package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    public Encomenda salvarPedido(Encomenda encomenda) {
        return encomendaRepository.save(encomenda);
    }
}
