package com.adriano.controledesac.converter;

import com.adriano.controledesac.model.Destinatario;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DestinatarioDTOToDestinatarioConverter implements Converter<Destinatario, com.adriano.controledesac.document.Destinatario> {
    @Override
    public com.adriano.controledesac.document.Destinatario convert(Destinatario dest) {
        return com.adriano.controledesac.document.Destinatario.builder()
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
