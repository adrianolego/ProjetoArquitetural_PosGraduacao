package com.adriano.controledesac.controledesac.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EncomendaDocument {

    @Id
    private String idEncomenda;
    private FreteDocument frete;
    private RemetenteDocument remetente ;
    private DestinatarioDocument destinatario ;
    private String dataHoraRecebimento ;
    private String nomeOperador ;
    private String observacao ;
}
