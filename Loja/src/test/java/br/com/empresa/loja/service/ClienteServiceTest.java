package br.com.empresa.loja.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.empresa.loja.model.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteServiceTest {
	
	
	@Autowired
	private ClienteService clienteService;
	
	private Cliente billClinton;
	private Cliente barackObama;
	private Cliente abrahamLincoln;

	@Before
	public void setUp() {
		
		billClinton = new Cliente();
		billClinton.setNome("Bill Clinton");
		billClinton.setEmail("billclinton@email.com");
		billClinton.setIdTimeDoCoracao("1");
		billClinton.setDataNascimento(LocalDate.of(1976, Month.AUGUST, 19));
		
		barackObama = new Cliente();
		barackObama.setNome("Barack Obama");
		barackObama.setEmail("barackobama@email.com");
		barackObama.setIdTimeDoCoracao("2");
		barackObama.setDataNascimento(LocalDate.of(1961, Month.AUGUST, 4));
		
		abrahamLincoln = new Cliente();
		abrahamLincoln.setNome("Abraham Lincoln");
		abrahamLincoln.setEmail("abrahamlincoln@email.com");
		abrahamLincoln.setIdTimeDoCoracao("3");
		abrahamLincoln.setDataNascimento(LocalDate.of(1809, Month.FEBRUARY, 12)); 
	}
	
	@Test
	public void teste01_validaInclusaoDeCliente() {
		
		Cliente retornoBillClinton = clienteService.cadastrar(billClinton);
		
		assertThat(billClinton.getNome(), equalTo(retornoBillClinton.getNome())); 
		assertThat(billClinton.getEmail(), equalTo(retornoBillClinton.getEmail())); 
		assertThat(billClinton.getIdTimeDoCoracao(), equalTo(retornoBillClinton.getIdTimeDoCoracao())); 
		assertThat(billClinton.getDataNascimento(), equalTo(retornoBillClinton.getDataNascimento()));
		
		Cliente retornoBarackObama = clienteService.cadastrar(barackObama);
		
		assertThat(barackObama.getNome(), equalTo(retornoBarackObama.getNome())); 
		assertThat(barackObama.getEmail(), equalTo(retornoBarackObama.getEmail())); 
		assertThat(barackObama.getIdTimeDoCoracao(), equalTo(retornoBarackObama.getIdTimeDoCoracao())); 
		assertThat(barackObama.getDataNascimento(), equalTo(retornoBarackObama.getDataNascimento()));
		
		Cliente retornoAbrahamLincoln = clienteService.cadastrar(abrahamLincoln);
		
		assertThat(abrahamLincoln.getNome(), equalTo(retornoAbrahamLincoln.getNome())); 
		assertThat(abrahamLincoln.getEmail(), equalTo(retornoAbrahamLincoln.getEmail())); 
		assertThat(abrahamLincoln.getIdTimeDoCoracao(), equalTo(retornoAbrahamLincoln.getIdTimeDoCoracao())); 
		assertThat(abrahamLincoln.getDataNascimento(), equalTo(retornoAbrahamLincoln.getDataNascimento()));
		
	}
	
	@Test
	public void teste02_validaListarTodos() {
		
		List<Cliente> retornoClientes = clienteService.listarTodos();
		
		assertThat(retornoClientes.size(), equalTo(3));
		
		Cliente retornoCliente0 = retornoClientes.get(0);
		assertThat(retornoCliente0.getNome(), equalTo("Bill Clinton")); 
		assertThat(retornoCliente0.getEmail(), equalTo("billclinton@email.com")); 
		assertThat(retornoCliente0.getIdTimeDoCoracao(), equalTo("1"));
		assertThat(retornoCliente0.getDataNascimento(), equalTo(LocalDate.of(1976, Month.AUGUST, 19)));
		
		Cliente retornoCliente1 = retornoClientes.get(1);
		assertThat(retornoCliente1.getNome(), equalTo("Barack Obama")); 
		assertThat(retornoCliente1.getEmail(), equalTo("barackobama@email.com")); 
		assertThat(retornoCliente1.getIdTimeDoCoracao(), equalTo("2"));
		assertThat(retornoCliente1.getDataNascimento(), equalTo(LocalDate.of(1961, Month.AUGUST, 4)));
		
		Cliente retornoCliente2 = retornoClientes.get(2);
		assertThat(retornoCliente2.getNome(), equalTo("Abraham Lincoln")); 
		assertThat(retornoCliente2.getEmail(), equalTo("abrahamlincoln@email.com")); 
		assertThat(retornoCliente2.getIdTimeDoCoracao(), equalTo("3"));
		assertThat(retornoCliente2.getDataNascimento(), equalTo(LocalDate.of(1809, Month.FEBRUARY, 12))); 

	}
}
