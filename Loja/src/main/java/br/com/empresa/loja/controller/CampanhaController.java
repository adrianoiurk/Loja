package br.com.empresa.loja.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.empresa.loja.model.Campanha;
import br.com.empresa.loja.service.CampanhaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/loja/campanhas")
@CrossOrigin(origins ="*")
@Api(value="API REST loja prova")
public class CampanhaController {

		@Autowired
		private CampanhaService campanhaService;
		
		@GetMapping
		@ApiOperation(value = "Retorna a lista de campanhas Ativas - listarCampanha().")
		public List<Campanha> listarCampanhas(){
			return campanhaService.listarCampanhasAtivas(LocalDate.now());
		}
		
		@GetMapping("/{id}")
		@ApiOperation(value = "Lista uma campanha - listarPorId().")
		public ResponseEntity<Campanha> listarPorId(@PathVariable String id){
			Optional<Campanha> optCampanha = campanhaService.listarPorId(id);
			
			if(optCampanha.isPresent()) {
				return ResponseEntity.ok(optCampanha.get());
			}
			
			return ResponseEntity.notFound().build();
		}
		
		@GetMapping("/timeDoCoracao/{idTimeDoCoracao}")
		@ApiOperation(value = "Lista de campanhas por Time do Coração - listarPorIdTimeDoCoracao().")
		public ResponseEntity<List<Campanha>> listarPorIdTimeDoCoracao(@PathVariable String idTimeDoCoracao){
			List<Campanha> campanhas = campanhaService.listarPorIdTimeDoCoracao(idTimeDoCoracao);
			
			if(!campanhas.isEmpty()) {
				return ResponseEntity.ok(campanhas);
			}
			
			return ResponseEntity.notFound().build();
		}
		
		@PostMapping
		@ApiOperation(value = "Cadastra uma campanha - cadastrarCampanha().")
		public ResponseEntity<Campanha> cadastrarCampanha(@RequestBody @Valid Campanha campanha, UriComponentsBuilder uriBuilder){

			campanhaService.cadastrar(campanha);
			URI uri = uriBuilder.path("/loja/campanhas/{id}").buildAndExpand(campanha.getId()).toUri();
			
			return ResponseEntity.created(uri).body(campanha);
			
		}
		
		@PutMapping("/{id}")
		@ApiOperation(value = "Atualiza uma campanha - atualizarCampanha().")
		public ResponseEntity<Campanha> atualizarCampanha(@PathVariable String id, @RequestBody @Valid Campanha campanha){
			Optional<Campanha> optCampanha = campanhaService.listarPorId(id);
			
			if(optCampanha.isPresent()) {
				campanhaService.atualizar(id, campanha);
				return ResponseEntity.ok(campanha);
			}
			
			return ResponseEntity.notFound().build();
			
		}
		
		@DeleteMapping("/{id}")
		@ApiOperation(value = "Remove uma campanha - remover().")
		public ResponseEntity<?> remover(@PathVariable String id) {
			
			Optional<Campanha> optCampanha = campanhaService.listarPorId(id);
			
			if(optCampanha.isPresent()) {
				campanhaService.remover(id);
				return ResponseEntity.ok().build();
			}
			
			return ResponseEntity.notFound().build();
			
		}	

}