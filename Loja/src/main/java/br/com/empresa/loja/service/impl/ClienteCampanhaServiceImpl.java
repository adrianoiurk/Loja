package br.com.empresa.loja.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.loja.model.Campanha;
import br.com.empresa.loja.model.Cliente;
import br.com.empresa.loja.model.ClienteCampanha;
import br.com.empresa.loja.repository.ClienteCampanhaRepository;
import br.com.empresa.loja.service.ClienteCampanhaService;

@Service
public class ClienteCampanhaServiceImpl implements ClienteCampanhaService {

	@Autowired
	private ClienteCampanhaRepository clienteCampanhaRepository;
	
	@Autowired
	private br.com.empresa.loja.service.CampanhaService campanhaService;

	@Override
	public List<ClienteCampanha> listarTodas() {
		return clienteCampanhaRepository.findAll();
	}

	@Override
	public Optional<List<ClienteCampanha>> listarPorIdCliente(String idCliente) {
		return clienteCampanhaRepository.findAllByIdCliente(idCliente);
	
	}

	@Override
	public Optional<List<ClienteCampanha>> listarPorIdCampanha(String idCampanha) {
		return clienteCampanhaRepository.findAllByIdCampanha(idCampanha);
	}

	@Override
	public List<ClienteCampanha> cadastrar(Cliente cliente) {
		List<ClienteCampanha> clienteCampanhas = new ArrayList<ClienteCampanha>();
		Optional<List<ClienteCampanha>> optClienteCampanhasByCliente = clienteCampanhaRepository.findAllByIdCliente(cliente.getId());
		List<Campanha> campanhasAtivasTimeCoracao = campanhaService.listarPorIdTimeDoCoracao(cliente.getIdTimeDoCoracao());
		
		if(optClienteCampanhasByCliente.isPresent()) {
			for(Campanha c: campanhasAtivasTimeCoracao) {
				ClienteCampanha cliCampanha = new ClienteCampanha(cliente.getId(), c.getId());
				if(!optClienteCampanhasByCliente.get().contains(cliCampanha)){
					clienteCampanhas.add(cliCampanha);
				}
			}
		}
		else {
			for(Campanha campanha: campanhasAtivasTimeCoracao) {
				ClienteCampanha clienteCampanha = new ClienteCampanha(cliente.getId(), campanha.getId());
				clienteCampanhas.add(clienteCampanha);
			}
		}
		return clienteCampanhaRepository.saveAll(clienteCampanhas);
	}

	@Override
	public void remover(String id) {
		clienteCampanhaRepository.deleteById(id);
		
	}
	
	@Override
	public Optional<ClienteCampanha> listarPorId(String id) {
		return clienteCampanhaRepository.findById(id);
	}
	
	

}
