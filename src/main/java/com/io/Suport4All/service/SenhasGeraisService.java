package com.io.Suport4All.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.Suport4All.dto.SenhasGeraisDto;
import com.io.Suport4All.entity.SenhasGeraisEntity;
import com.io.Suport4All.repository.SenhasGeraisRepository;

@Service
public class SenhasGeraisService {
	
	@Autowired
	private SenhasGeraisRepository senhaRepository;
	
	
	//Create
	
	public SenhasGeraisDto createSenha(SenhasGeraisDto senhaDto) {
		
		SenhasGeraisEntity senhaEntity = new SenhasGeraisEntity();
		
		//Preciso criar as validações para todas as classes. Essa está errada!
		
//		if(senhaDto.getLogin().isBlank() || senhaDto.getOrigem().isBlank() || senhaDto.getSenha().isBlank()) {
//			throw new RuntimeException("O login não pode ser vaziu!");
//		}
//		
		
		senhaEntity.setLogin(senhaDto.getLogin());
		senhaEntity.setDescricao(senhaDto.getDescricao());
		senhaEntity.setEmail(senhaDto.getEmail());
		senhaEntity.setOrigem(senhaDto.getOrigem());
		senhaEntity.setSenha(senhaDto.getSenha());
		
		senhaRepository.save(senhaEntity);
		
		return new SenhasGeraisDto(senhaEntity);
		
	}
	
	
	//Update
	
	public SenhasGeraisDto updateSenha(SenhasGeraisDto senhaDto, Long id) {
		
		Optional<SenhasGeraisEntity> senhaEntity = senhaRepository.findById(id);
		
		if(!senhaEntity.isPresent()) {
			throw new RuntimeException("Não foi possivel encontrar a senha com id: "+ id);
		}
		
		
		SenhasGeraisEntity senhaMod = senhaEntity.get();
		
		senhaMod.setLogin(senhaDto.getLogin());
		senhaMod.setEmail(senhaDto.getEmail());
		senhaMod.setDescricao(senhaDto.getDescricao());
		senhaMod.setOrigem(senhaDto.getOrigem());
		senhaMod.setSenha(senhaDto.getSenha());
		
		senhaRepository.save(senhaMod);
		
		return new SenhasGeraisDto(senhaMod);
		
	}
	
	
	//find by id
	
	public SenhasGeraisDto findSenhaById(Long id) {
		
		Optional<SenhasGeraisEntity> senhaEntity = senhaRepository.findById(id);
		
		if(!senhaEntity.isPresent()) {
			throw new RuntimeException("A senha com id: "+ id + ", não existe no sistema!");
		}
		
		return new SenhasGeraisDto(senhaEntity.get());
		
	}
	
	
	// ListAll
	
	public List<SenhasGeraisDto> findAllSenhas(){
		
		List<SenhasGeraisEntity> senhasEntity = senhaRepository.findAll();
		
		List<SenhasGeraisDto> senhasDto = new ArrayList<>();
		
		if(senhasEntity.isEmpty()) {
			throw new RuntimeException("Nenhuma senha encontrada!");
		}
		for (SenhasGeraisEntity senhasEnt : senhasEntity) {
			senhasDto.add(new SenhasGeraisDto(senhasEnt));
		}
		return senhasDto;
	}
	
	
	// Encontrar por titulo
	
	public List<SenhasGeraisDto> findAllByOrigem(String origem){
		
		List<SenhasGeraisEntity> senhasEntity = senhaRepository.findByOrigem(origem);
		
		List<SenhasGeraisDto> senhaDto = new ArrayList<>(); 
		
		for(SenhasGeraisEntity senhaEnt: senhasEntity) {
			senhaDto.add(new SenhasGeraisDto(senhaEnt));
		}
		
		return senhaDto;
		
	}
	
	
	
}
