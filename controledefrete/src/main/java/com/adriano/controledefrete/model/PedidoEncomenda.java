package com.adriano.controledefrete.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEncomenda implements Serializable {

    private String idEncomenda;
    private Frete frete;
    private Remetente remetente;
    private Destinatario destinatario;
    private String dataHoraRecebimento;
    private String nomeOperador;
    private String observacao;
}
