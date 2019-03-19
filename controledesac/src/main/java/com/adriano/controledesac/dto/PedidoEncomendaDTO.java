package com.adriano.controledesac.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class PedidoEncomendaDTO implements Serializable {

    @JsonIgnore
    private String idEncomenda;
    private FreteDTO frete;
    private RemetenteDTO remetente;
    private DestinatarioDTO destinatario;
    @JsonIgnore
    private String dataHoraRecebimento;
    @JsonIgnore
    private String nomeOperador;
    private String observacao;

    public String getIdEncomenda() {
        return idEncomenda;
    }

    public PedidoEncomendaDTO setIdEncomenda(String idEncomenda) {
        this.idEncomenda = idEncomenda;
        return this;
    }

    public FreteDTO getFrete() {
        return frete;
    }

    public PedidoEncomendaDTO setFrete(FreteDTO frete) {
        this.frete = frete;
        return this;
    }

    public RemetenteDTO getRemetente() {
        return remetente;
    }

    public PedidoEncomendaDTO setRemetente(RemetenteDTO remetente) {
        this.remetente = remetente;
        return this;
    }

    public DestinatarioDTO getDestinatario() {
        return destinatario;
    }

    public PedidoEncomendaDTO setDestinatario(DestinatarioDTO destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public String getDataHoraRecebimento() {
        return dataHoraRecebimento;
    }

    public PedidoEncomendaDTO setDataHoraRecebimento(String dataHoraRecebimento) {
        this.dataHoraRecebimento = dataHoraRecebimento;
        return this;
    }

    public String getNomeOperador() {
        return nomeOperador;
    }

    public PedidoEncomendaDTO setNomeOperador(String nomeOperador) {
        this.nomeOperador = nomeOperador;
        return this;
    }

    public String getObservacao() {
        return observacao;
    }

    public PedidoEncomendaDTO setObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    @Override
    public String toString() {
        return "PedidoEncomendaDTO{" +
                "idEncomenda='" + idEncomenda + '\'' +
                ", frete=" + frete +
                ", remetente=" + remetente +
                ", destinatario=" + destinatario +
                ", dataHoraRecebimento='" + dataHoraRecebimento + '\'' +
                ", nomeOperador='" + nomeOperador + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}