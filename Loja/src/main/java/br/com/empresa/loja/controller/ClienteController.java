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
import br.com.empresa.loja.repository.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/loja/clientes")
@CrossOrigin(origins ="*")
@Api(value="API REST loja prova")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	@ApiOperation(value = "Retorna a lista de clientes - listarClientes().")
	public List<Cliente> listarClientes(){
		return clienteRepository.findAll();
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastra um cliente - cadastrarCliente().")
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder){
		
		clienteRepository.save(cliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cliente);
		
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Lista um cliente - listarPorId().")
	public ResponseEntity<Cliente> listarPorId(@PathVariable String id){
		Optional<Cliente> optCliente = clienteRepository.findById(id);
		
		if(optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um cliente - atualizarCliente().")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable String id, @RequestBody @Valid Cliente cliente){
		Optional<Cliente> optCliente = clienteRepository.findById(id);
		
		if(optCliente.isPresent()) {
			clienteRepository.save(cliente);
			return ResponseEntity.ok(cliente);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um cliente - remover().")
	public ResponseEntity<?> remover(@PathVariable String id) {
		Optional<Cliente> optCliente = clienteRepository.findById(id);
		
		if(optCliente.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}	

}
