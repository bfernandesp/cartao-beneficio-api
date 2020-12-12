package br.com.cartao.beneficio.util;

public class Utils {
	
	public static String onlyNumbers(String str) {
		if (str != null) {
			return str.replaceAll("[^0123456789]", "");
		} else {
			return "";
		}
	}
}
