package com.adriano.controledesac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String idEncomenda;
    private Frete frete;
    private Remetente remetente;
    private Destinatario destinatario;
    @JsonIgnore
    private LocalDateTime dataHoraRecebimento;
    @JsonIgnore
    private String nomeOperador;
    private String observacao;


}