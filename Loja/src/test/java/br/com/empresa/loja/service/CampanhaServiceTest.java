package br.com.empresa.loja.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.exparity.hamcrest.date.LocalDateMatchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.empresa.loja.model.Campanha;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CampanhaServiceTest {
	
	@Autowired
	private CampanhaService campanhaService;
	
	private Campanha campanha1;
	private Campanha campanha2;
	private Campanha campanha3;
	
	@Before
	public void setUp() {
		campanha1 = new Campanha();
		campanha1.setNome("Campanha 1");
		campanha1.setIdTimeDoCoracao("1");
		campanha1.setDataIniVigencia(LocalDate.of(2019, Month.OCTOBER, 01));
		campanha1.setDataFimVigencia(LocalDate.of(2019, Month.OCTOBER, 02));

		campanha2 = new Campanha();
		campanha2.setNome("Campanha 2");
		campanha2.setIdTimeDoCoracao("1");
		campanha2.setDataIniVigencia(LocalDate.of(2019, Month.OCTOBER, 01));
		campanha2.setDataFimVigencia(LocalDate.of(2019, Month.OCTOBER, 02));
		
		campanha3 = new Campanha();
		campanha3.setNome("Campanha 3");
		campanha3.setIdTimeDoCoracao("2");
		campanha3.setDataIniVigencia(LocalDate.of(2019, Month.OCTOBER, 01));
		campanha3.setDataFimVigencia(LocalDate.of(2019, Month.OCTOBER, 03));
		
	}
	
	@Test
	public void teste01_validaInclusaoDeNovaCampanha() {
		
		
		Campanha retornoCampanha1 = campanhaService.cadastrar(campanha1);
		
		assertThat(campanha1.getNome(), equalTo(retornoCampanha1.getNome())); 
		assertThat(campanha1.getIdTimeDoCoracao(), equalTo(retornoCampanha1.getIdTimeDoCoracao())); 
		assertThat(campanha1.getDataIniVigencia(), equalTo(retornoCampanha1.getDataIniVigencia())); 
		assertThat(campanha1.getDataFimVigencia(), equalTo(retornoCampanha1.getDataFimVigencia())); 

		Campanha retornoCampanha2 = campanhaService.cadastrar(campanha2);
		
		assertThat(campanha2.getNome(), equalTo(retornoCampanha2.getNome())); 
		assertThat(campanha2.getIdTimeDoCoracao(), equalTo(retornoCampanha2.getIdTimeDoCoracao())); 
		assertThat(campanha2.getDataIniVigencia(), equalTo(retornoCampanha2.getDataIniVigencia())); 
		assertThat(campanha2.getDataFimVigencia(), equalTo(retornoCampanha2.getDataFimVigencia()));
	
	}
	
	@Test
	public void teste02_validaListaPorIdNovaCampanha() {
		
		List<Campanha> campanhasAtivas = campanhaService.listarCampanhasAtivas();
		
		assertThat(campanhasAtivas.size(), equalTo(2));
		assertThat(campanhasAtivas.get(0).getNome(), equalTo("Campanha 1")); 
		assertThat(campanhasAtivas.get(0).getDataIniVigencia(), 
				LocalDateMatchers.within(1, ChronoUnit.DAYS, (LocalDate.of(2019, Month.OCTOBER, 01))));
		assertThat(campanhasAtivas.get(0).getDataFimVigencia(), 
				LocalDateMatchers.within(1, ChronoUnit.DAYS, (LocalDate.of(2019, Month.OCTOBER, 03))));
		
		assertThat(campanhasAtivas.get(1).getNome(), equalTo("Campanha 2")); 
		assertThat(campanhasAtivas.get(1).getDataIniVigencia(), 
				LocalDateMatchers.within(1, ChronoUnit.DAYS, (LocalDate.of(2019, Month.OCTOBER, 01))));
		assertThat(campanhasAtivas.get(1).getDataFimVigencia(), 
				LocalDateMatchers.within(1, ChronoUnit.DAYS, (LocalDate.of(2019, Month.OCTOBER, 02))));
		
	}
	
	@Test
	public void teste03_validaInclusaoDaCampanha3RegraSomaDataVigencia() {
		Campanha retornoCampanha3 = campanhaService.cadastrar(campanha3);
		List<Campanha> campanhasAtivas = campanhaService.listarCampanhasAtivas();
		
		assertThat(campanhasAtivas.size(), equalTo(3));
		
		assertThat(campanha3.getNome(), equalTo("Campanha 3")); 
		assertThat(campanha3.getIdTimeDoCoracao(), equalTo(retornoCampanha3.getIdTimeDoCoracao())); 
		
		assertThat(campanhasAtivas.get(0).getDataIniVigencia(), equalTo(LocalDate.of(2019, Month.OCTOBER, 01))); 
		assertThat(campanhasAtivas.get(0).getDataFimVigencia(), equalTo(LocalDate.of(2019, Month.OCTOBER, 05)));
		
		assertThat(campanhasAtivas.get(1).getDataIniVigencia(), equalTo(LocalDate.of(2019, Month.OCTOBER, 01))); 
		assertThat(campanhasAtivas.get(1).getDataFimVigencia(), equalTo(LocalDate.of(2019, Month.OCTOBER, 04)));
		
		assertThat(campanhasAtivas.get(2).getDataIniVigencia(), equalTo(LocalDate.of(2019, Month.OCTOBER, 01))); 
		assertThat(campanhasAtivas.get(2).getDataFimVigencia(), equalTo(LocalDate.of(2019, Month.OCTOBER, 03)));
		
	}
	
	@Test
	public void teste04_validaAlteracaoDaCampanha() {
		
		Campanha resultCampanha = campanhaService.listarPorIdTimeDoCoracao("2").get(0);
		resultCampanha.setNome("Campanha 3 - ALTERADA");

		resultCampanha = campanhaService.atualizar(resultCampanha.getId(), resultCampanha);
		
		assertThat(resultCampanha.getNome(), equalTo("Campanha 3 - ALTERADA")); 
	}
}
