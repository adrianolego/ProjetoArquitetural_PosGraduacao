package com.arquitetura.ModuloColeta.model.interfaces;

import java.util.List;

public interface FreteInterface {

    FreteInterface obterValorFrete();

    public static <T extends Comparable> T calcularMenorFrete(List<T> lista) {
        if (lista.isEmpty()) {
            System.out.println("Lista esta vazia !");
        }

        T maximo = lista.get(0);
        for (T item : lista) {
            if (item.compareTo(maximo) > 0) {
                maximo = item;
            }
        }

        return maximo;
    }

}