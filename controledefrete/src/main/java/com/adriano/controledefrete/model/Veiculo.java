package com.adriano.controledefrete.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
