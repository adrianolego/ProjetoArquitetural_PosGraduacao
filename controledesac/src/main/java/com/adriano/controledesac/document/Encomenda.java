package com.adriano.controledesac.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Encomenda implements Serializable {

    private String idEncomenda;
    private Frete frete;
    private Remetente remetente;
    private Destinatario destinatario;


    private LocalDateTime dataHoraRecebimento;
    private String nomeOperador;
    private String observacao;
}
