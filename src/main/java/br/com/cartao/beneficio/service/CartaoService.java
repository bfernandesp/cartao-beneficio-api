package br.com.cartao.beneficio.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cartao.beneficio.entity.Cartao;
import br.com.cartao.beneficio.repository.CartaoRepository;
import br.com.cartao.beneficio.util.TipoCartaoEnum;
import br.com.cartao.beneficio.util.Utils;
import br.com.cartao.beneficio.vo.CartaoVo;
import br.com.cartao.beneficio.vo.Usd;

@Service
public class CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	public Usd obterCotacaoDolarDia() {
		return cartaoRepository.obterCotacaoDolarDia();
	}

	/**
	 * Salvar Cartao
	 * @param cartao
	 * @throws Exception 
	 */
	public void salvarCartao(CartaoVo cartao) throws Exception {
		Cartao entity = new Cartao();
		entity.setNomeEmpresa(cartao.getNomeEmpresa());
		entity.setNomeFuncionario(cartao.getNomeFuncionario());
		entity.setNumero(cartao.getNumero());
		entity.setValidadeMes(cartao.getValidadeMes());
		entity.setValidadeAno(cartao.getValidadeAno());
		entity.setFlagTipoBeneficio(cartao.getFlagTipoBeneficio());
		validarCampos(entity);
		cartaoRepository.save(entity);
	}
	
	public void alterarCartao(Cartao cartao) throws Exception {
		validarCampos(cartao);
		cartaoRepository.save(cartao);
	}
	
	public List<Cartao> obterTodos() {
		List<Cartao> list = cartaoRepository.findAll();
		Usd usd = this.obterCotacaoDolarDia();
		list.stream().forEach((Cartao c) -> {
			c.setSaldoConvertidoEmDolar((c.getSaldo() != null ? 
					c.getSaldo() : 
						BigDecimal.ZERO).multiply(BigDecimal.valueOf(usd.getHigh()), MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_EVEN));
		});
		return list;
	}
	
	public void delete(Long id) {
		cartaoRepository.deleteById(id);
	}
	
	public void validarCampos(Cartao cartao) throws Exception {
		if (cartao == null) {
			throw new Exception("Preecher campos");
		}
		if (cartao.getFlagTipoBeneficio() == null 
				|| (!cartao.getFlagTipoBeneficio().equals(TipoCartaoEnum.ALIMENTACAO.getTipo())
				&& !cartao.getFlagTipoBeneficio().equals(TipoCartaoEnum.REFEICAO.getTipo()))) {
			throw new Exception("FlagTipoBeneficio com " + TipoCartaoEnum.ALIMENTACAO.getTipo() + " ou " + TipoCartaoEnum.REFEICAO.getTipo());
		}
		if(cartao.getValidadeMes() == null || cartao.getValidadeMes() < 1 || cartao.getValidadeMes() > 12) {
			throw new Exception("Mês de validade precisa ser entre 1 e 12");
		}
		Calendar cal = Calendar.getInstance();
		int anoMais2 = cal.get(Calendar.YEAR) + 2;
		if(cartao.getValidadeAno() == null || cartao.getValidadeAno() < anoMais2) {
			throw new Exception("Ano validade deve ser pelo menos 2 anos a mais que o ano atual");
		}
		if (cartao.getNumero() == null || Utils.onlyNumbers(cartao.getNumero()).length() < 16) {
			throw new Exception("Preencha os numeros do cartao no formato 0000.0000.0000.0000");
		}
	}
}
