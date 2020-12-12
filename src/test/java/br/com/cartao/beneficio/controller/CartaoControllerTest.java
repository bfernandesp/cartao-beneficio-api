package br.com.cartao.beneficio.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.gson.Gson;

import br.com.cartao.beneficio.entity.Cartao;
import br.com.cartao.beneficio.vo.CartaoVo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CartaoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void salvar() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/cartao")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(obterCartaoVoMock())));
		
		assertTrue(new Gson().fromJson(result.andReturn().getResponse().getContentAsString(), String.class).equals("OK"));		
	}
	
	@Test
	public void alterar() throws Exception {
		//Salva para depois alterar
		salvar();
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/cartao")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(obterCartaoMock())));
		
		assertTrue(new Gson().fromJson(result.andReturn().getResponse().getContentAsString(), String.class).equals("OK"));		
	}
	
	@Test 
	public void listarTodos() throws Exception {
		salvar();
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/cartao")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print());
		
		@SuppressWarnings("unchecked")
		List<Cartao> listaRetorno = new ArrayList<Cartao>(new Gson().fromJson(result.andReturn().getResponse().getContentAsString(), ArrayList.class));
		assertTrue(listaRetorno != null && !listaRetorno.isEmpty());
	}
	
	@Test 
	public void deletar() throws Exception {
		salvar();
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/cartao")
				.param("idCartao", String.valueOf(1))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		assertEquals(result.andReturn().getResponse().getContentAsString(), "OK");
	}	
	
	public CartaoVo obterCartaoVoMock() {
		CartaoVo cartao = new CartaoVo();
		cartao.setNomeEmpresa("Petrobrás");
		cartao.setNomeFuncionario("Bruno Fernandes");
		cartao.setNumero("1234.4567.3435.4654");
		cartao.setValidadeMes(12);
		cartao.setValidadeAno(2025);
		cartao.setFlagTipoBeneficio("A");
		cartao.setSaldo(BigDecimal.valueOf(523));
		return cartao;
	}
	
	public Cartao obterCartaoMock() {
		CartaoVo cartao = this.obterCartaoVoMock();
		Cartao entity = new Cartao();
		entity.setId(1L);
		entity.setNomeEmpresa(cartao.getNomeEmpresa());
		entity.setNomeFuncionario(cartao.getNomeFuncionario());
		entity.setNumero(cartao.getNumero());
		entity.setValidadeMes(cartao.getValidadeMes());
		entity.setValidadeAno(cartao.getValidadeAno());
		entity.setFlagTipoBeneficio(cartao.getFlagTipoBeneficio());
		return entity;
	}

}
