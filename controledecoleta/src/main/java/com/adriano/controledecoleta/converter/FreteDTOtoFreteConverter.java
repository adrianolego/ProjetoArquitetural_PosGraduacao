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
                .dataColeta(freteDTO.getDataColeta())
                .prioridadeEnvio(freteDTO.getPrioridadeEnvio())
                .tipoCarga(freteDTO.getTipoCarga())
                .existeCargaRetorno(freteDTO.isExisteCargaRetorno())
                .build();
    }
}
