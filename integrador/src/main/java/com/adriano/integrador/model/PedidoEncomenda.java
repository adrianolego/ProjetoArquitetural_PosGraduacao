package com.adriano.integrador.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEncomenda implements Serializable {

    private String idEncomenda;
    private Frete frete;
    private Remetente remetente;
    private Destinatario destinatario;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHoraRecebimento ;
    private String nomeOperador;
    private String observacao;
    private Boolean expedicao;
}
