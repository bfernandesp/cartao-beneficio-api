package br.com.cartao.beneficio.repository.custom.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

import br.com.cartao.beneficio.repository.AbstractRepository;
import br.com.cartao.beneficio.repository.custom.CartaoRepositoryCustom;
import br.com.cartao.beneficio.vo.Usd;

public class CartaoRepositoryCustomImpl extends AbstractRepository implements CartaoRepositoryCustom {

	@SuppressWarnings("rawtypes")
	@Override
	public Usd obterCotacaoDolarDia() {
		Map<String,Object> params = new HashMap<>();
		
		ResponseEntity response = requestApi(params, "https://economia.awesomeapi.com.br/all/USD-BRL", HttpMethod.GET, Object.class);
		
		if (response != null && response.getBody() != null) {
			System.out.println(response.getBody());
			
			String jsonUsd = new Gson().toJson(response.getBody());
			jsonUsd = jsonUsd.substring(1, jsonUsd.length()-1).replace("\"USD\":", "");
			System.out.println(jsonUsd);
			
			Usd cotacaoDolar = new Gson().fromJson(jsonUsd, Usd.class);
			//assertTrue(c != null && c.getCityName().equals("Porto Seguro"));
			
//			ObjectMapper mapper = new ObjectMapper();
//			
//			Usd cotacaoDolar = mapper.convertValue(response.getBody(),
//		    		new TypeReference<Usd>() {});
			
			return cotacaoDolar;
			
		}
		
		return null;
	}

}
