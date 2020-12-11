package br.com.cartao.beneficio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cartao.beneficio.entity.Cartao;
import br.com.cartao.beneficio.repository.custom.CartaoRepositoryCustom;

@Repository
public interface CartaoRepository extends CartaoRepositoryCustom, JpaRepository<Cartao, Long> {
	
}
