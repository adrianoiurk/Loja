package br.com.empresa.loja.service;

import java.util.List;
import java.util.Optional;

import br.com.empresa.loja.model.Cliente;

public interface ClienteService {

	List<Cliente> listarTodos();
	Optional<Cliente> listarPorId(String id);
	Cliente cadastrar(Cliente cliente);
	Cliente atualizar(String id, Cliente cliente);
	void remover(String id);
	
}
