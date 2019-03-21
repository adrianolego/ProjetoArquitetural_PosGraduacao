package com.adriano.controledecoleta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Frete  implements Serializable {

    private Double distanciaKM;
    private String urgencia;
    private String classificacaoEnvio;
    private String classificacaoTransporte;
    private boolean existeCargaRetorno;
}
