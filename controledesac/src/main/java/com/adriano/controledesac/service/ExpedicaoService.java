package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.document.Expedicao;
import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpedicaoService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    public void atualizarExpedicao(Encomenda encomenda) {
        Optional<Encomenda> atualizarExpedicao =
                encomendaRepository.findById(encomenda.getIdEncomenda());
        // valida se a encomenda já foi enviada anteriormente e pode estar sendo reenviada
        List<Expedicao> listExpedicao = atualizarExpedicao.get().getExpedicao();
        if (listExpedicao != null && !listExpedicao.isEmpty()) {
            listExpedicao.addAll(listExpedicao);
        }

        if (encomenda.getFrete() != null) {
            listExpedicao.addAll(encomenda.getExpedicao());
        }

        atualizarExpedicao.get().setExpedicao(listExpedicao);

        encomendaRepository.save(atualizarExpedicao.get());
    }

}
