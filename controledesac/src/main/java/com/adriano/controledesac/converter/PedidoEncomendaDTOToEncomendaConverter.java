package com.adriano.controledesac.converter;

import com.adriano.controledesac.document.Encomenda;
import com.adriano.controledesac.model.PedidoEncomenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PedidoEncomendaDTOToEncomendaConverter implements Converter<PedidoEncomenda, Encomenda> {

    @Autowired
    private DestinatarioDTOToDestinatarioConverter destinatarioDTOToDestinatarioConverter;

    @Autowired
    private RemetenteDTOtoRemetenteConverter remetenteDTOtoRemetenteConverter;

    @Autowired
    private FreteDTOtoFreteConverter freteDTOtoFreteConverter;

    @Override
    public Encomenda convert(PedidoEncomenda pedidoDTO) {

        return Encomenda.builder()
                .idEncomenda(pedidoDTO.getIdEncomenda())
                .dataHoraRecebimento(pedidoDTO.getDataHoraRecebimento())
                .observacao(Arrays.asList(pedidoDTO.getObservacao()))
                .nomeOperador(Arrays.asList(pedidoDTO.getNomeOperador()))
                .destinatario(destinatarioDTOToDestinatarioConverter.convert(pedidoDTO.getDestinatario()))
                .remetente(remetenteDTOtoRemetenteConverter.convert(pedidoDTO.getRemetente()))
                .frete(Arrays.asList(freteDTOtoFreteConverter.convert(pedidoDTO.getFrete())))
                .build();
    }
}
