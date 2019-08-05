package br.com.empresa.loja.service.impl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.loja.model.Campanha;
import br.com.empresa.loja.repository.CampanhaRepository;
import br.com.empresa.loja.service.CampanhaService;

@Service
public class CampanhaServiceImpl implements CampanhaService {
	
	private static final int ONE_DAY = 1;
	

	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Override
	public List<Campanha> listarTodas() {
		return campanhaRepository.findAll();
	}

	@Override
	public Optional<Campanha> listarPorId(String id) {
		
		return campanhaRepository.findById(id);
	}
	
	@Override
	public List<Campanha> listarPorIdTimeDoCoracao(String idTimeDoCoracao) {
		
		return campanhaRepository.findAllWithDataFimVigenciaAfterByidTimeDoCoracao(idTimeDoCoracao, LocalDate.now());
		
	}

	@Override
	public Campanha cadastrar(Campanha campanha) {
		
		List<Campanha> campanhasVigentesNoPeriodo = campanhaRepository.findAllByOrderByDataFimVigenciaAsc(campanha.getDataIniVigencia(), campanha.getDataFimVigencia());
		campanhasVigentesNoPeriodo.sort(Comparator.comparing(Campanha::getDataFimVigencia));
		
		for(Campanha camp: campanhasVigentesNoPeriodo) {
			this.somarUmDiaDataFimVigencia(camp);
			if(camp.getDataFimVigencia().isEqual(campanha.getDataFimVigencia())) {
				this.somarUmDiaDataFimVigencia(camp);
			}
			for(Campanha camp2: campanhasVigentesNoPeriodo) {
				if(!camp.getId().equals(camp2.getId())) {
					if(camp.getDataFimVigencia().isEqual(camp2.getDataFimVigencia())) {
						this.somarUmDiaDataFimVigencia(camp);
						break;
					}
				}
			}
		}
		return campanhaRepository.save(campanha);
	}

	@Override
	public Campanha atualizar(String id, Campanha campanha) {
		
//		TODO Incluir pushing para avisar outros sistemas que houve alteração nas campanhas existentes 
		return campanhaRepository.save(campanha);
		
	}

	@Override
	public void remover(String id) {
		campanhaRepository.deleteById(id); 

	}

	@Override
	public List<Campanha> listarCampanhasAtivas() {
		
		return campanhaRepository.findAllWithDataFimVigenciaAfter(LocalDate.now());
	}
	
	private Campanha somarUmDiaDataFimVigencia(Campanha campanha) {
		
		campanha.setDataFimVigencia(campanha.getDataFimVigencia().plusDays(ONE_DAY));
		this.atualizar(campanha.getId(), campanha);

		return campanha;
	}
}
