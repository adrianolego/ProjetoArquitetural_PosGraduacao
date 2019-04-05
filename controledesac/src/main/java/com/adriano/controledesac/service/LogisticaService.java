package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.document.Logistica;
import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogisticaService {
    @Autowired
    private EncomendaRepository encomendaRepository;

    public void atualizarLogistica(Encomenda encomenda) {
        Optional<Encomenda> atualizarLogistica =
                encomendaRepository.findById(encomenda.getIdEncomenda());
        // valida se a encomenda já foi enviada anteriormente e pode estar sendo reenviada
        List<Logistica> listLogistica = atualizarLogistica.get().getLogistica();
        if (listLogistica != null && !listLogistica.isEmpty()) {
            listLogistica.addAll(listLogistica);
        }

        if (encomenda.getFrete() != null) {
            listLogistica.addAll(encomenda.getLogistica());
        }

        atualizarLogistica.get().setLogistica(listLogistica);

        encomendaRepository.save(atualizarLogistica.get());
    }
}
