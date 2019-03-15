package com.adriano.controledefrete.model;

import java.io.Serializable;

public class PedidoEncomenda  implements Serializable {

    private String idEncomenda;
    private Frete frete;
    private Remetente remetente ;
    private Destinatario destinatario ;
    private String dataHoraRecebimento ;
    private String nomeOperador ;
    private String observacao ;

    public String getIdEncomenda() {
        return idEncomenda;
    }

    public PedidoEncomenda setIdEncomenda(String idEncomenda) {
        this.idEncomenda = idEncomenda;
        return this;
    }

    public Frete getFrete() {
        return frete;
    }

    @Override
    public String toString() {
        return "PedidoEncomenda{" +
                "idEncomenda='" + idEncomenda + '\'' +
                ", frete=" + frete +
                ", remetente=" + remetente +
                ", destinatario=" + destinatario +
                ", dataHoraRecebimento='" + dataHoraRecebimento + '\'' +
                ", nomeOperador='" + nomeOperador + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }

    public PedidoEncomenda setFrete(Frete frete) {
        this.frete = frete;
        return this;
    }

    public Remetente getRemetente() {
        return remetente;
    }

    public PedidoEncomenda setRemetente(Remetente remetente) {
        this.remetente = remetente;
        return this;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public PedidoEncomenda setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public String getDataHoraRecebimento() {
        return dataHoraRecebimento;
    }

    public PedidoEncomenda setDataHoraRecebimento(String dataHoraRecebimento) {
        this.dataHoraRecebimento = dataHoraRecebimento;
        return this;
    }

    public String getNomeOperador() {
        return nomeOperador;
    }

    public PedidoEncomenda setNomeOperador(String nomeOperador) {
        this.nomeOperador = nomeOperador;
        return this;
    }

    public String getObservacao() {
        return observacao;
    }

    public PedidoEncomenda setObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }
}
