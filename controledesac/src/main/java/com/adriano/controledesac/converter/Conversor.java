package com.adriano.controledesac.converter;

import com.adriano.controledesac.document.EncomendaDocument;
import com.adriano.controledesac.dto.PedidoEncomendaDTO;
import com.adriano.controledesac.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Conversor extends EntityMapper<PedidoEncomendaDTO, EncomendaDocument> {

    EncomendaDocument paraDocument(PedidoEncomendaDTO obj);
}
