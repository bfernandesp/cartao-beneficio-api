package br.com.cartao.beneficio.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCartaoEnum {
	
	ALIMENTACAO("A", "Alimentação"),
	REFEICAO("R", "Refeição");
	
	private final String tipo;
	private final String nome;
	
    public static TipoCartaoEnum getById(String tipo) {
    	TipoCartaoEnum result = null;
        for ( TipoCartaoEnum tp : TipoCartaoEnum.values() ){
            if (tp.tipo.equals(tipo)) {
                result = tp;
                break;
            }
        }
        return result;
    }
}
