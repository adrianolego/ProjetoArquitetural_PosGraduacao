package com.adriano.controledesac.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class EncomendaDocument implements Serializable {

    @Id
    private String idEncomenda;
    private Frete frete;
    private Remetente remetente;
    private Destinatario destinatario;
    private String dataHoraRecebimento;
    private String nomeOperador;
    private String observacao;

    public String getIdEncomenda() {
        return idEncomenda;
    }

    public EncomendaDocument setIdEncomenda(String idEncomenda) {
        this.idEncomenda = idEncomenda;
        return this;
    }

    public Frete getFrete() {
        return frete;
    }

    public EncomendaDocument setFrete(Frete frete) {
        this.frete = frete;
        return this;
    }

    public Remetente getRemetente() {
        return remetente;
    }

    public EncomendaDocument setRemetente(Remetente remetente) {
        this.remetente = remetente;
        return this;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public EncomendaDocument setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public String getDataHoraRecebimento() {
        return dataHoraRecebimento;
    }

    public EncomendaDocument setDataHoraRecebimento(String dataHoraRecebimento) {
        this.dataHoraRecebimento = dataHoraRecebimento;
        return this;
    }

    public String getNomeOperador() {
        return nomeOperador;
    }

    public EncomendaDocument setNomeOperador(String nomeOperador) {
        this.nomeOperador = nomeOperador;
        return this;
    }

    public String getObservacao() {
        return observacao;
    }

    public EncomendaDocument setObservacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    @Override
    public String toString() {
        return "EncomendaDocument{" +
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
