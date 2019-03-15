package com.adriano.controledecoleta.dto;

import java.io.Serializable;

public class RemetenteDTO implements Serializable {

    private String nome;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;
    private String telefone;
    private String celular;
    private String email;

    public String getNome() {
        return nome;
    }

    public RemetenteDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public RemetenteDTO setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public RemetenteDTO setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public RemetenteDTO setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public RemetenteDTO setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public RemetenteDTO setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public RemetenteDTO setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getCelular() {
        return celular;
    }

    public RemetenteDTO setCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RemetenteDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "Remetente{" +
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
