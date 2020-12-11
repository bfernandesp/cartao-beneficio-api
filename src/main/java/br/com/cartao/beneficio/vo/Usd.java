package br.com.cartao.beneficio.vo;

import lombok.Data;

@Data
public class Usd {
	
	private String code;
	private String codein;
	private String name;
	private Double high;
	private Double low;
	private Double varBid;
	private Double pctChange;
	private Double bid;
	private Double ask;
	private Long timestamp;
	private String create_date;
	
//	USD={code=USD, 
//			codein=BRL, 
//			name=Dólar Comercial, 
//			high=5.0883, 
//			low=5.0259, 
//			varBid=0.0328, 
//			pctChange=0.65, 
//			bid=5.058, 
//			ask=5.0588, 
//			timestamp=1607719800, 
//			create_date=2020-12-11 17:50:02}}

}
