package com.adriano.controledesac.service;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.document.Veiculo;
import com.adriano.controledesac.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    public void atualizarVeiculo(Encomenda encomenda) {
        Optional<Encomenda> atualizarVeiculo =
                encomendaRepository.findById(encomenda.getIdEncomenda());
        // valida se a encomenda já foi enviada anteriormente e pode estar sendo reenviada
        List<Veiculo> listVeiculo = atualizarVeiculo.get().getVeiculo();
        if (listVeiculo != null && !listVeiculo.isEmpty()) {
            listVeiculo.addAll(listVeiculo);
        }

        if (encomenda.getFrete() != null) {
            listVeiculo.addAll(encomenda.getVeiculo());
        }

        atualizarVeiculo.get().setVeiculo(listVeiculo);

        encomendaRepository.save(atualizarVeiculo.get());
    }
}
