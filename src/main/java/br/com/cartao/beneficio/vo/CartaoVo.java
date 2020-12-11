package br.com.cartao.beneficio.vo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CartaoVo {
	
	private String nomeFuncionario;
	
	private String nomeEmpresa;
	
	private String numero;
	
	private String validadeMes;
	
	private Integer validadeAno;
	
	private String flagTipoBeneficio;

}
