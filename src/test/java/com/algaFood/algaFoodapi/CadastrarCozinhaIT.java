package com.algaFood.algaFoodapi;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaFood.algaFoodapi.domain.exception.CozinhaNaoEncontradoException;
import com.algaFood.algaFoodapi.domain.exception.EntidadeEmUsoException;
import com.algaFood.algaFoodapi.domain.model.Cozinha;
import com.algaFood.algaFoodapi.domain.service.CozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastrarCozinhaIT {
    
     @Autowired
	CozinhaService cozinhaService;
	
	@Test
	public void deveAtribuirId_quandoCadastroCozinhaComDadosCorretos() {
		Cozinha cozinha = new Cozinha();
		 cozinha.setNome("chinesa");
		 cozinha = cozinhaService.adicionar(cozinha);
		 assertThat(cozinha.getId()).isNotNull();
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_quandoCadastraCozinhaSemNome() {
		Cozinha cozinha = new Cozinha();
		 cozinha.setNome(null);
		 cozinha = cozinhaService.adicionar(cozinha);

	}
	@Test(expected = EntidadeEmUsoException.class)
	public void deveFalhar_quandoExcluirCozinhaEmUso() {
		 cozinhaService.remover(1L);

	}
	
	@Test(expected = CozinhaNaoEncontradoException.class)
	public void deveFalhar_quandoExcluirCozinhaInexistente() {
			cozinhaService.remover(30L);

	}

}
