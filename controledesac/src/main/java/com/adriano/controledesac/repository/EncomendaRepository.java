package com.adriano.controledesac.repository;

import com.adriano.controledesac.document.Encomenda;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends MongoRepository<Encomenda, String> {
}
