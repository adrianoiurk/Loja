package br.com.empresa.loja.service;

import java.util.List;
import java.util.Optional;

import br.com.empresa.loja.model.Cliente;
import br.com.empresa.loja.model.ClienteCampanha;

public interface ClienteCampanhaService {

	List<ClienteCampanha> listarTodas();
	Optional<ClienteCampanha> listarPorId(String id);
	Optional<List<ClienteCampanha>> listarPorIdCliente(String idCliente);
	Optional<List<ClienteCampanha>> listarPorIdCampanha(String idCampanha);
	List<ClienteCampanha> cadastrar(Cliente cliente);
	void remover(String id);
	
}
