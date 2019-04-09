package com.adriano.controledeexpedicao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Remetente {

    private String nome;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;
    private String telefone;
    private String celular;
    private String email;
}
