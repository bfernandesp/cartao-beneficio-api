package br.com.cartao.beneficio.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "cartao")
public class Cartao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nomeFuncionario;
	
	private String nomeEmpresa;
	
	private String numero;
	
	private Integer validadeMes;
	
	private Integer validadeAno;
	
	private String flagTipoBeneficio;
	
	private BigDecimal saldo;
	
	@Transient
	private BigDecimal saldoConvertidoEmDolar;
	
	public Cartao() {};
}
