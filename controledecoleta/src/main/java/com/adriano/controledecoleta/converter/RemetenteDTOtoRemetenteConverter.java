package com.adriano.controledecoleta.converter;

import com.adriano.controledecoleta.dto.RemetenteDTO;
import com.adriano.controledecoleta.model.Remetente;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RemetenteDTOtoRemetenteConverter implements Converter<RemetenteDTO, Remetente> {
    @Override
    public Remetente convert(RemetenteDTO rem) {
        return Remetente.builder()
                .bairro(rem.getBairro())
                .celular(rem.getCelular())
                .cep(rem.getCep())
                .cidade(rem.getCidade())
                .email(rem.getEmail())
                .logradouro(rem.getLogradouro())
                .nome(rem.getNome())
                .numero(rem.getNumero())
                .telefone(rem.getTelefone())
                .build();
    }
}
