package br.com.cartao.beneficio.vo;

import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CartaoVo {
	
	private String nomeFuncionario;
	
	private String nomeEmpresa;
	
	private String numero;
	
	private Integer validadeMes;
	
	private Integer validadeAno;
	
	private String flagTipoBeneficio;
	
	private BigDecimal saldo;

}
