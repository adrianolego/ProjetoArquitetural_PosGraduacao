package com.adriano.controledesac.repository;

import com.adriano.controledesac.document.EncomendaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends MongoRepository<EncomendaDocument, String> {
}
