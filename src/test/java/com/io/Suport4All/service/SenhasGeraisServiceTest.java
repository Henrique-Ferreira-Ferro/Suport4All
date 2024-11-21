package com.io.Suport4All.service;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.io.Suport4All.dto.SenhasGeraisDto;
import com.io.Suport4All.entity.SenhasGeraisEntity;
import com.io.Suport4All.repository.SenhasGeraisRepository;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SenhasGeraisServiceTest {

	@Mock
	private SenhasGeraisRepository senhaRepository;
	
	@InjectMocks
	private SenhasGeraisService senhaService;
	
	
//	senhaEntity.setLogin(senhaDto.getLogin());
//	senhaEntity.setDescricao(senhaDto.getDescricao());
//	senhaEntity.setEmail(senhaDto.getEmail());
//	senhaEntity.setOrigem(senhaDto.getOrigem());
//	senhaEntity.setSenha(senhaDto.getSenha());
	@Test
	public void deveCriarSenha() {
		
		var senhaEntity = mock(SenhasGeraisDto.class);
		
		given(senhaEntity.getId()).willReturn(1L);
		given(senhaEntity.getLogin()).willReturn("tiLi");
		given(senhaEntity.getDescricao()).willReturn("Local onde se encontra os registros dos dominios da empresa!");
		given(senhaEntity.getEmail()).willReturn("ti@gmail.com");
		given(senhaEntity.getOrigem()).willReturn("RegistroBr");
		given(senhaEntity.getSenha()).willReturn("14512");
		
//		given(senhaRepository.save(senhaEntity)).willReturn(senhaEntity);
//
//		var result = senhaService.createSenha(senhaEntity);
		
	}
	
	@Test 
	public void deveAtualizarSenha() {
		
	}
	
	@Test
	public void deveEncontrarSenhaPeloId() {
		
	}
	
	@Test
	public void deveEncontrarTodasAsSenhas() {
		
	}
	
	@Test
	public void deveEncontrarSenhaPorOrigem() {
		
	}
	
	@Test
	public void deveDeletarSenhaPorId() {
		
	}
	
	
	@Test
	public void deveLancarExcecaoQuandoNaoEncontrarSenhaAoEditar() {
		
	}
	
	
	
}
