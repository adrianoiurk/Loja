package br.com.empresa.loja.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.loja.model.Cliente;
import br.com.empresa.loja.repository.ClienteRepository;
import br.com.empresa.loja.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> listarPorId(String id) {
		
		return clienteRepository.findById(id);
	}

	@Override
	public Cliente cadastrar(Cliente cliente) {

		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente atualizar(String id, Cliente cliente) {
		return clienteRepository.save(cliente);
		
	}

	@Override
	public void remover(String id) {
		clienteRepository.deleteById(id);

	}

}
