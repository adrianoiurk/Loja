package br.com.empresa.loja.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.empresa.loja.model.Campanha;

public interface CampanhaRepository extends MongoRepository<Campanha, String> {
	
	@Query("{ 'idTimeDoCoracao' : {$eq: ?0}, 'dataFimVigencia' : { $gte: ?1 } }")
	public List<Campanha> findAllWithDataFimVigenciaAfterByidTimeDoCoracao(@Param("idTimeDoCoracao") String idTimeDoCoracao, @Param("dataAtual") LocalDate dataAtual);
	
	@Query("{ 'dataFimVigencia' : { $gte: ?0 } } ")
	public List<Campanha> findAllWithDataFimVigenciaAfter(@Param("dataAtual") LocalDate dataAtual);
	
	@Query("{'$or':[ { 'dataIniVigencia': { $lte: ?0}, 'dataFimVigencia': { $gte: ?0 } } , { 'dataIniVigencia': { $lte: ?1}, 'dataFimVigencia': { $gte: ?1 } } ]}")
	public List<Campanha> findAllByOrderByDataFimVigenciaAsc( @Param("dataIniVigenciaNova") LocalDate dataIniVigencia, @Param("dataFimVigenciaNova") LocalDate dataFimVigencia);

}