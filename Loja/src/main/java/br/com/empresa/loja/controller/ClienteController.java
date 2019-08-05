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

import br.com.empresa.loja.model.Cliente;
import br.com.empresa.loja.model.ClienteCampanha;
import br.com.empresa.loja.service.ClienteCampanhaService;
import br.com.empresa.loja.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/loja/clientes")
@CrossOrigin(origins ="*")
@Api(value="API REST loja prova")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteCampanhaService clienteCampanhaService;
	
	@GetMapping
	@ApiOperation(value = "Retorna a lista de clientes - listarClientes().")
	public List<Cliente> listarClientes(){
		return clienteService.listarTodos();
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastra um cliente - cadastrarCliente().")
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder){
		
		clienteService.cadastrar(cliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cliente);
		
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Lista um cliente - listarPorId().")
	public ResponseEntity<Cliente> listarPorId(@PathVariable String id){
		Optional<Cliente> optCliente = clienteService.listarPorId(id);
		
		if(optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um cliente - atualizarCliente().")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable String id, @RequestBody @Valid Cliente cliente){
		Optional<Cliente> optCliente = clienteService.listarPorId(id);
		
		if(optCliente.isPresent()) {
			clienteService.atualizar(id, cliente);
			return ResponseEntity.ok(cliente);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um cliente - remover().")
	public ResponseEntity<?> remover(@PathVariable String id) {
		Optional<Cliente> optCliente = clienteService.listarPorId(id);
		
		if(optCliente.isPresent()) {
			clienteService.remover(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}	
	
	@PostMapping(path="/clienteCampanhas/")
	@ApiOperation(value = "Cadastra uma associação de cliente e campanha - cadastrarClienteCampanha().")
	public ResponseEntity<List<ClienteCampanha>> cadastrarClienteCampanha(@RequestBody Cliente cliente, UriComponentsBuilder uriBuilder){
		
		List<ClienteCampanha> clienteCampanha = clienteCampanhaService.cadastrar(cliente);
		URI uri = uriBuilder.path("/clienteCampanhas/{id}").buildAndExpand(clienteCampanha).toUri();
		
		return ResponseEntity.created(uri).body(clienteCampanha);
	}
	
	@GetMapping(path="/clienteCampanhas/")
	@ApiOperation(value = "Retorna a lista de associação de clientes e campanhas - listarClienteCampanhas().")
	public List<ClienteCampanha> listarClienteCampanhas(){
		return clienteCampanhaService.listarTodas();
	}
	
	@DeleteMapping("/clienteCampanhas/{id}")
	@ApiOperation(value = "Remove uma associação de cliente e campanha - remover().")
	public ResponseEntity<?> removerClienteCampanha(@PathVariable String id) {
		Optional<ClienteCampanha> optClienteCampanha = clienteCampanhaService.listarPorId(id);
		
		if(optClienteCampanha.isPresent()) {
			clienteCampanhaService.remover(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}	

}
