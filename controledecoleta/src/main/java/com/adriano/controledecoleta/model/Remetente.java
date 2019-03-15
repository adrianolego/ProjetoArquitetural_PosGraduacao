package com.adriano.controledecoleta.model;

import java.io.Serializable;

public class Remetente implements Serializable {

    private String idEncomenda;
    private String nome;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;
    private String telefone;
    private String celular;
    private String email;

    public String getIdEncomenda() {
        return idEncomenda;
    }

    public Remetente setIdEncomenda(String idEncomenda) {
        this.idEncomenda = idEncomenda;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Remetente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Remetente setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public Remetente setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Remetente setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Remetente setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Remetente setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Remetente setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getCelular() {
        return celular;
    }

    public Remetente setCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Remetente setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "Remetente{" +
                "idEncomenda='" + idEncomenda + '\'' +
                ", nome='" + nome + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cep='" + cep + '\'' +
                ", telefone='" + telefone + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
