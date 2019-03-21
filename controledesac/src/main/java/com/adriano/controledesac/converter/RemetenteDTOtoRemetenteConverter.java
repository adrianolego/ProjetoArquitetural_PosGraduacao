package com.adriano.controledesac.converter;

import com.adriano.controledesac.model.Remetente;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RemetenteDTOtoRemetenteConverter implements Converter<Remetente, com.adriano.controledesac.document.Remetente> {
    @Override
    public com.adriano.controledesac.document.Remetente convert(Remetente rem) {
        return com.adriano.controledesac.document.Remetente.builder()
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
