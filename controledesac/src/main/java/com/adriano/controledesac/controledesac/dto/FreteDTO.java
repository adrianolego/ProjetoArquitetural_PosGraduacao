package com.adriano.controledesac.controledesac.dto;

import java.io.Serializable;

public class FreteDTO implements Serializable {

    private Double distanciaKM;
    private String urgencia;
    private String classificacaoEnvio;
    private String classificacaoTransporte;
    private boolean existeCargaRetorno;

    public Double getDistanciaKM() {
        return distanciaKM;
    }

    public FreteDTO setDistanciaKM(Double distanciaKM) {
        this.distanciaKM = distanciaKM;
        return this;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public FreteDTO setUrgencia(String urgencia) {
        this.urgencia = urgencia;
        return this;
    }

    public String getClassificacaoEnvio() {
        return classificacaoEnvio;
    }

    public FreteDTO setClassificacaoEnvio(String classificacaoEnvio) {
        this.classificacaoEnvio = classificacaoEnvio;
        return this;
    }

    public String getClassificacaoTransporte() {
        return classificacaoTransporte;
    }

    public FreteDTO setClassificacaoTransporte(String classificacaoTransporte) {
        this.classificacaoTransporte = classificacaoTransporte;
        return this;
    }

    public boolean isExisteCargaRetorno() {
        return existeCargaRetorno;
    }

    public FreteDTO setExisteCargaRetorno(boolean existeCargaRetorno) {
        this.existeCargaRetorno = existeCargaRetorno;
        return this;
    }

    @Override
    public String toString() {
        return "Frete{" +
                ", distanciaKM=" + distanciaKM +
                ", urgencia='" + urgencia + '\'' +
                ", classificacaoEnvio='" + classificacaoEnvio + '\'' +
                ", classificacaoTransporte='" + classificacaoTransporte + '\'' +
                ", existeCargaRetorno=" + existeCargaRetorno +
                '}';
    }
}
