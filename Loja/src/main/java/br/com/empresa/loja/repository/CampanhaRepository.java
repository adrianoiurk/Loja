package br.com.empresa.loja.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.empresa.loja.model.Campanha;

public interface CampanhaRepository extends MongoRepository<Campanha, String> {

}
