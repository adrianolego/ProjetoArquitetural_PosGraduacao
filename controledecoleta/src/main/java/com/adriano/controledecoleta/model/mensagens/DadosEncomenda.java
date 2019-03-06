package com.adriano.controledecoleta.model.mensagens;

import com.adriano.controledecoleta.model.PedidoEncomenda;

public interface DadosEncomenda {

    void sendPrivateMail(PedidoEncomenda encomenda);

    void sendPrivateInternalMail(PedidoEncomenda encomenda);
}
