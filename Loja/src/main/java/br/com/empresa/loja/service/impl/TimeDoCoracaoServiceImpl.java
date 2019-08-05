package br.com.empresa.loja.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.loja.model.TimeDoCoracao;
import br.com.empresa.loja.repository.TimeDoCoracaoRepository;
import br.com.empresa.loja.service.TimeDoCoracaoService;

@Service
public class TimeDoCoracaoServiceImpl implements TimeDoCoracaoService {

	@Autowired
	private TimeDoCoracaoRepository timeDoCoracaoRepository;
	
	@Override
	public List<TimeDoCoracao> listarTodos() {
		return timeDoCoracaoRepository.findAll();
	}

	@Override
	public Optional<TimeDoCoracao> listarPorId(String id) {
		
		return timeDoCoracaoRepository.findById(id);
	}

	@Override
	public TimeDoCoracao cadastrar(TimeDoCoracao timeDoCoracao) {

		return timeDoCoracaoRepository.save(timeDoCoracao);
	}

	@Override
	public TimeDoCoracao atualizar(String id, TimeDoCoracao timeDoCoracao) {
		return timeDoCoracaoRepository.save(timeDoCoracao);
		
	}

	@Override
	public void remover(String id) {
		timeDoCoracaoRepository.deleteById(id);

	}

}
