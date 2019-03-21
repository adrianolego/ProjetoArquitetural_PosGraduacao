package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.model.PedidoEncomenda;
import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FreteService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    public Encomenda atualizarFrete(PedidoEncomenda encomenda) {
        Optional<Encomenda> atualizaEncomenda =
                encomendaRepository.findById(encomenda.getIdEncomenda());

        return atualizaEncomenda.get();
    }
}
