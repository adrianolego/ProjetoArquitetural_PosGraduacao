package com.adriano.controledecoleta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEncomendaDTO implements Serializable {

    private FreteDTO frete;
    private RemetenteDTO remetente;
    private DestinatarioDTO destinatario;
    private String nomeOperador;
}