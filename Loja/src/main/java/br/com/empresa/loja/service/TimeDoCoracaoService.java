package br.com.empresa.loja.service;

import java.util.List;
import java.util.Optional;

import br.com.empresa.loja.model.TimeDoCoracao;

public interface TimeDoCoracaoService {

	List<TimeDoCoracao> listarTodos();
	Optional<TimeDoCoracao> listarPorId(String id);
	TimeDoCoracao cadastrar(TimeDoCoracao timeDoCoracao);
	TimeDoCoracao atualizar(String id, TimeDoCoracao timeDoCoracao);
	void remover(String id);
	
}
