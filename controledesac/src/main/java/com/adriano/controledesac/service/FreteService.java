package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.document.Frete;
import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreteService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    public void atualizarFrete(Encomenda encomenda) {
        Optional<Encomenda> atualizarFrete =
                encomendaRepository.findById(encomenda.getIdEncomenda());
        // valida se a encomenda já foi enviada anteriormente e pode estar sendo reenviada
        List<Frete> listFrete = atualizarFrete.get().getFrete();
        if (listFrete != null && !listFrete.isEmpty()) {
            listFrete.addAll(listFrete);
        }

        if (encomenda.getFrete() != null) {
            listFrete.addAll(encomenda.getFrete());
        }

        atualizarFrete.get().setFrete(listFrete);

        encomendaRepository.save(atualizarFrete.get());
    }
}
