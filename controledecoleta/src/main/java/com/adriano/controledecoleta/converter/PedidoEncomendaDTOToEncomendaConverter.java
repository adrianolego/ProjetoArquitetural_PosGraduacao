package com.adriano.controledecoleta.converter;

import com.adriano.controledecoleta.dto.PedidoEncomendaDTO;
import com.adriano.controledecoleta.model.PedidoEncomenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PedidoEncomendaDTOToEncomendaConverter implements Converter<PedidoEncomendaDTO, PedidoEncomenda> {

    @Autowired
    private DestinatarioDTOToDestinatarioConverter destinatarioDTOToDestinatarioConverter;

    @Autowired
    private RemetenteDTOtoRemetenteConverter remetenteDTOtoRemetenteConverter;

    @Autowired
    private FreteDTOtoFreteConverter freteDTOtoFreteConverter;

    @Override
    public PedidoEncomenda convert(PedidoEncomendaDTO pedidoDTO) {

        return PedidoEncomenda.builder()
                .nomeOperador(pedidoDTO.getNomeOperador())
                .destinatario(destinatarioDTOToDestinatarioConverter.convert(pedidoDTO.getDestinatario()))
                .remetente(remetenteDTOtoRemetenteConverter.convert(pedidoDTO.getRemetente()))
                .frete(freteDTOtoFreteConverter.convert(pedidoDTO.getFrete()))
                .dataHoraRecebimento(null)
                .expedicao(null)
                .logistica(null)
                .veiculo(null)
                .calculoFrete(null)
                .observacao(null)
                .build();
    }
}
