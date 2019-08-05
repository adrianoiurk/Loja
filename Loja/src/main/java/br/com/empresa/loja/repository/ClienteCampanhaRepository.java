package br.com.empresa.loja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.empresa.loja.model.ClienteCampanha;

public interface ClienteCampanhaRepository extends MongoRepository<ClienteCampanha, String> {
	
	public Optional<List<ClienteCampanha>> findAllByIdCliente(String idCliente);
	public Optional<List<ClienteCampanha>> findAllByIdCampanha(String idCampanha);

}