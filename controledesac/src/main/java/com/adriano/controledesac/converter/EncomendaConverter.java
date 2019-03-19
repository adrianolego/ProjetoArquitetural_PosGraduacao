package com.adriano.controledesac.converter;

import com.adriano.controledesac.document.EncomendaDocument;
import com.adriano.controledesac.dto.PedidoEncomendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EncomendaConverter implements Converter<PedidoEncomendaDTO, EncomendaDocument> {

//    @Autowired
//    private Conversor conversor;

    @Override
    public EncomendaDocument convert(PedidoEncomendaDTO pedidoDTO) {
//   return conversor.paraDocument(pedidoDTO);
        return null;
    }
}
