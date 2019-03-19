package com.adriano.controledesac.mapper;

import java.util.List;

public interface EntityMapper<D, E> {

    E paraDocument(D dto);

    List<E> paraObjeto(List<D> dtoList);
}
