package com.adriano.controledesac.converter;

import com.adriano.controledesac.model.Frete;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FreteDTOtoFreteConverter implements Converter<Frete, com.adriano.controledesac.document.Frete> {
    @Override
    public com.adriano.controledesac.document.Frete convert(Frete freteDTO) {
        return com.adriano.controledesac.document.Frete.builder()
                .classificacaoEnvio(freteDTO.getClassificacaoEnvio())
                .classificacaoTransporte(freteDTO.getClassificacaoTransporte())
                .distanciaKM(freteDTO.getDistanciaKM())
                .existeCargaRetorno(freteDTO.isExisteCargaRetorno())
                .urgencia(freteDTO.getUrgencia())
                .build();
    }
}
