package br.com.cartao.beneficio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartao.beneficio.entity.Cartao;
import br.com.cartao.beneficio.service.CartaoService;
import br.com.cartao.beneficio.vo.CartaoVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cartao")
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="Obter todos", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno com todos os Cartoes."),
			@ApiResponse(code = 400, message = "Erro na busca dos Cartoes.")
	})
	@GetMapping
	public ResponseEntity obterTodos() {
		log.info("Buscando Cartoes ");
		return ResponseEntity.status(HttpStatus.OK)
				.body(cartaoService.obterTodos());
		
	}	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="Salvar Cartao", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno com OK."),
			@ApiResponse(code = 400, message = "Erro ao Salvar Cartao.")
	})
	@PostMapping
	public ResponseEntity salvarCartao(@RequestBody CartaoVo cartao) {
		log.info("Salvando Cartao > " + cartao);
		
		try {
			cartaoService.salvarCartao(cartao);
			return ResponseEntity.status(HttpStatus.OK)
					.body("OK");
		}catch (Exception e) {
			log.error("Erro ao salvar cartão >> " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
	}	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="Alterar Cartao", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno com OK de cartao alterado."),
			@ApiResponse(code = 400, message = "Erro ao alterar Cartao.")
	})
	@PutMapping
	public ResponseEntity alterarCartao(@RequestBody Cartao cartao) {
		log.info("Alterar Cartao > " + cartao);
		
		try {
			cartaoService.alterarCartao(cartao);
			return ResponseEntity.status(HttpStatus.OK)
					.body("OK");
		}catch (Exception e) {
			log.error("Erro ao Alterar Cartao >> " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="Deletar Cartao", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno com OK de cartao deletado."),
			@ApiResponse(code = 400, message = "Erro ao deletar Cartao.")
	})
	@DeleteMapping
	public ResponseEntity deletarCartao(@RequestParam(value="idCartao", required=true) Long idCartao) {
		log.info("Deletar Cartao ID > " + idCartao);
		
		try {
			cartaoService.delete(idCartao);
			return ResponseEntity.status(HttpStatus.OK)
					.body("OK");
		}catch (Exception e) {
			log.error("Erro ao Deletar Cartao >> " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
	}	

}
