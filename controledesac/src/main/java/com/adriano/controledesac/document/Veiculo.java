package com.adriano.controledesac.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {

    String codigo;
    String descricao;
    String placa;
    Double pesoMaximoKg;
    Integer quantidadeEixos;
}
