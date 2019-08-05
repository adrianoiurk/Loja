package br.com.empresa.loja.controller;

import java.net.URI;
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

import br.com.empresa.loja.model.TimeDoCoracao;
import br.com.empresa.loja.service.TimeDoCoracaoService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/loja/timeDoCoracao")
@CrossOrigin(origins ="*")
@Api(value="API REST loja prova")
public class TimeDoCoracaoController {
	
	@Autowired
	private TimeDoCoracaoService timeDoCoracaoService;
	
	@GetMapping
	public List<TimeDoCoracao> listarTimeDoCoracaos(){
		return timeDoCoracaoService.listarTodos();
	}
	
	@PostMapping
	public ResponseEntity<TimeDoCoracao> cadastrarTimeDoCoracao(@RequestBody @Valid TimeDoCoracao timeDoCoracao, UriComponentsBuilder uriBuilder){
		
		timeDoCoracaoService.cadastrar(timeDoCoracao);
		URI uri = uriBuilder.path("/timeDoCoracao/{id}").buildAndExpand(timeDoCoracao.getId()).toUri();
		
		return ResponseEntity.created(uri).body(timeDoCoracao);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TimeDoCoracao> listarPorId(@PathVariable String id){
		Optional<TimeDoCoracao> optTimeDoCoracao = timeDoCoracaoService.listarPorId(id);
		
		if(optTimeDoCoracao.isPresent()) {
			return ResponseEntity.ok(optTimeDoCoracao.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TimeDoCoracao> atualizarTimeDoCoracao(@PathVariable String id, @RequestBody @Valid TimeDoCoracao timeDoCoracao){
		Optional<TimeDoCoracao> optTimeDoCoracao = timeDoCoracaoService.listarPorId(id);
		
		if(optTimeDoCoracao.isPresent()) {
			timeDoCoracaoService.atualizar(id, timeDoCoracao);
			return ResponseEntity.ok(timeDoCoracao);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable String id) {
		Optional<TimeDoCoracao> optTimeDoCoracao = timeDoCoracaoService.listarPorId(id);
		
		if(optTimeDoCoracao.isPresent()) {
			timeDoCoracaoService.remover(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}	

}