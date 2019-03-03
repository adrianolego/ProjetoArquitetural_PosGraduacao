package com.arquitetura.ModuloColeta.model;

import com.arquitetura.ModuloColeta.model.enuns.OrigemFreteEnum;
import com.arquitetura.ModuloColeta.model.interfaces.FreteInterface;

public class Frete implements FreteInterface, Comparable<Frete> {

    private Long id;
    private String origem;
    private Double valorFrete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Frete(OrigemFreteEnum origem) {
        this.setOrigem(origem.toString());
    }

    @Override
    public Frete obterValorFrete() {
        return null;
    }

    @Override
    public int compareTo(Frete frete) {
        return valorFrete.compareTo(frete.getValorFrete());
    }
}
