package com.adriano.controledecoleta.sender;

import com.adriano.controledecoleta.model.PedidoEncomenda;

public interface DadosEncomenda {

    void enviarDadosEncomendaFila(PedidoEncomenda encomenda);

}
