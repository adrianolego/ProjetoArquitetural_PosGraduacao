package com.adriano.controledesac.controledesac.repository;

import com.adriano.controledesac.controledesac.document.EncomendaDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EncomendaRepository extends ReactiveMongoRepository<EncomendaDocument, String > {
}
