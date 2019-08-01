package br.com.empresa.loja.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.empresa.loja.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

}
