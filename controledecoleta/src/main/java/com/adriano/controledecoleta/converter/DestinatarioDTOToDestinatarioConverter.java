package com.adriano.controledecoleta.converter;

import com.adriano.controledecoleta.dto.DestinatarioDTO;
import com.adriano.controledecoleta.model.Destinatario;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DestinatarioDTOToDestinatarioConverter implements Converter<DestinatarioDTO, Destinatario> {
    @Override
    public Destinatario convert(DestinatarioDTO dest) {
        return Destinatario.builder()
                .bairro(dest.getBairro())
                .celular(dest.getCelular())
                .cep(dest.getCep())
                .cidade(dest.getCidade())
                .email(dest.getEmail())
                .logradouro(dest.getLogradouro())
                .nome(dest.getNome())
                .numero(dest.getNumero())
                .telefone(dest.getTelefone())
                .build();
    }
}
