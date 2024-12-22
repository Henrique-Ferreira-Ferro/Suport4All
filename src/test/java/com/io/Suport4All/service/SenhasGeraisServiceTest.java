package com.io.Suport4All.service;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.io.Suport4All.dto.SenhasGeraisDto;
import com.io.Suport4All.entity.SenhasGeraisEntity;
import com.io.Suport4All.repository.SenhasGeraisRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SenhasGeraisServiceTest {

	@Mock
	private SenhasGeraisRepository senhaRepository;
	
	@InjectMocks
	private SenhasGeraisService senhaService;
	
	//private ModelMapper modelMapper = new ModelMapper();
//	senhaEntity.setLogin(senhaDto.getLogin());
//	senhaEntity.setDescricao(senhaDto.getDescricao());
//	senhaEntity.setEmail(senhaDto.getEmail());
//	senhaEntity.setOrigem(senhaDto.getOrigem());
//	senhaEntity.setSenha(senhaDto.getSenha());
	@Test
	public void deveCriarSenha() {
		
//		var senhaEntity = mock(SenhasGeraisEntity.class);
//		
//		given(senhaEntity.getId()).willReturn(1L);
//		given(senhaEntity.getLogin()).willReturn("tiLi");
//		given(senhaEntity.getDescricao()).willReturn("Local onde se encontra os registros dos dominios da empresa!");
//		given(senhaEntity.getEmail()).willReturn("ti@gmail.com");
//		given(senhaEntity.getOrigem()).willReturn("RegistroBr");
//		given(senhaEntity.getSenha()).willReturn("14512");
//		
//		var senhaDTO = modelMapper.map(senhaEntity, SenhasGeraisDto.class);
//		
//		given(senhaRepository.save(senhaEntity)).willReturn(senhaEntity);
//		var result = senhaService.createSenha(senhaDTO);
//		
//		assertEquals(result.getId(), senhaDTO.getId());
//		assertEquals(result.getLogin(), senhaDTO.getLogin());
//		assertEquals(result.getDescricao(), senhaDTO.getDescricao());
//		assertEquals(result.getEmail(), senhaDTO.getEmail());
//		assertEquals(result.getOrigem(), senhaDTO.getOrigem());
//		assertEquals(result.getSenha(), senhaDTO.getSenha());
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
