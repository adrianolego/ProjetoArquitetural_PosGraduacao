package com.adriano.controledesac.model;

import com.adriano.controledesac.enuns.PrioridadeEnvioEnum;
import com.adriano.controledesac.enuns.TipoCargaEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
public class Frete implements Serializable {

    private PrioridadeEnvioEnum prioridadeEnvio;
    private TipoCargaEnum tipoCarga;
    private boolean existeCargaRetorno;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataColeta;
    private Double pesoKg;

}
