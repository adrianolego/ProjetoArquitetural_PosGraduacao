package com.adriano.controledecoleta.converter;

import com.adriano.controledecoleta.dto.FreteDTO;
import com.adriano.controledecoleta.model.Frete;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FreteDTOtoFreteConverter implements Converter<FreteDTO, Frete> {
    @Override
    public Frete convert(FreteDTO freteDTO) {
        return Frete.builder()
                .classificacaoEnvio(freteDTO.getClassificacaoEnvio())
                .classificacaoTransporte(freteDTO.getClassificacaoTransporte())
                .distanciaKM(freteDTO.getDistanciaKM())
                .existeCargaRetorno(freteDTO.isExisteCargaRetorno())
                .urgencia(freteDTO.getUrgencia())
                .build();
    }
}
