package com.adriano.controledesac.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Encomenda implements Serializable {

    private String idEncomenda;
    private List<Frete> frete;
    private Remetente remetente;
    private Destinatario destinatario;
    private LocalDateTime dataHoraRecebimento;
    private List<String> nomeOperador;
    private List<String> observacao;
    private List<Logistica> logistica;
    private List<Expedicao> expedicao;
    private List<Veiculo> veiculo;
}
