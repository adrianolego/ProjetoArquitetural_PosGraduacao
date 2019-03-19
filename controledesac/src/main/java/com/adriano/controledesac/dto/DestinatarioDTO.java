package com.adriano.controledesac.dto;

import java.io.Serializable;

public class DestinatarioDTO implements Serializable {

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

    public DestinatarioDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public DestinatarioDTO setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public DestinatarioDTO setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public DestinatarioDTO setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public DestinatarioDTO setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public DestinatarioDTO setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public DestinatarioDTO setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getCelular() {
        return celular;
    }

    public DestinatarioDTO setCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DestinatarioDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "Destinatario{" +
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
