package com.io.Suport4All.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.io.Suport4All.repository.SenhasGeraisRepository;

@RunWith(MockitoJUnitRunner.class)
public class SenhasGeraisServiceTest {

	@Mock
	private SenhasGeraisRepository senhaRepository;
	
	@InjectMocks
	private SenhasGeraisService senhaService;
	
	@Test
	public void deveCriarSenha() {
		
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
