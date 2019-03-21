package com.adriano.controledecoleta.dto;

import com.adriano.controledecoleta.enums.PrioridadeEnvioEnum;
import com.adriano.controledecoleta.enums.TipoCargaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreteDTO implements Serializable {

    private PrioridadeEnvioEnum prioridadeEnvio;
    private TipoCargaEnum tipoCarga;
    private boolean existeCargaRetorno;
    private LocalDate dataColeta;
}
