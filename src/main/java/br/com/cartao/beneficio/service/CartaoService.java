package br.com.cartao.beneficio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cartao.beneficio.entity.Cartao;
import br.com.cartao.beneficio.repository.CartaoRepository;
import br.com.cartao.beneficio.util.TipoCartaoEnum;
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
		return cartaoRepository.findAll();
	}
	
	public void delete(Long id) {
		cartaoRepository.deleteById(id);
	}
	
	public void validarCampos(Cartao cartao) throws Exception {
		if (cartao == null) {
			throw new Exception("Preecher campos");
		}
		if (cartao.getFlagTipoBeneficio() == null 
				|| (!cartao.getFlagTipoBeneficio().equalsIgnoreCase(TipoCartaoEnum.ALIMENTACAO.getTipo())
				&& !cartao.getFlagTipoBeneficio().equalsIgnoreCase(TipoCartaoEnum.REFEICAO.getTipo()))) {
			throw new Exception("FlagTipoBeneficio com " + TipoCartaoEnum.ALIMENTACAO.getTipo() + " ou " + TipoCartaoEnum.REFEICAO.getTipo());
		}
	}
}
