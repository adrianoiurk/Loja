package br.com.empresa.loja.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.empresa.loja.model.TimeDoCoracao;

public interface TimeDoCoracaoRepository extends MongoRepository<TimeDoCoracao, String> {

}
