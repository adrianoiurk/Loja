package br.com.empresa.loja.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CampanhaControllerTest {

	@Autowired
	public WebApplicationContext context;
	
	@Autowired
	private CampanhaController campanhaController;
	
	
	private MockMvc mockmvc;
	
	@Before
	public void setUp() {
		this.mockmvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
    @Test
    public void teste01_contexLoads() throws Exception {
        assertThat(campanhaController).isNotNull();
    }
	
	@Test
	public void teste02_validaGETCampanhasRetornoNOK() throws Exception{
		String uri = "/loja/campanhas/x";
		
		this.mockmvc.perform(get(uri)).andExpect(status().isNotFound());

	}
	
	@Test
	public void teste03_validaGETCampanhasRetornoOK() throws Exception{
		
		String uri = "/loja/campanhas/";
		this.mockmvc.perform(get(uri)
			.contentType(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOk())
		    .andExpect(content()
		    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//		    .andExpect(jsonPath("$[0].nome", is("Campanha 1")))
			.andExpect(status().isOk());
	}
}
