package br.com.empresa.loja.service;

import java.util.List;
import java.util.Optional;

import br.com.empresa.loja.model.Campanha;

public interface CampanhaService {

	List<Campanha> listarTodas();
	List<Campanha> listarCampanhasAtivas();
	Optional<Campanha> listarPorId(String id);
	List<Campanha> listarPorIdTimeDoCoracao(String idTimeDoCoracao);
	Campanha cadastrar(Campanha campanha);
	Campanha atualizar(String id, Campanha campanha);
	void remover(String id);
	
}
