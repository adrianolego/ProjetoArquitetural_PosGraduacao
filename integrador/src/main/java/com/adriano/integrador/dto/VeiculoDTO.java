package com.adriano.integrador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoDTO {

    String codigo;
    String descricao;
    String placa;
    Double pesoMaximoKg;
    Integer quantidadeEixos;
}
