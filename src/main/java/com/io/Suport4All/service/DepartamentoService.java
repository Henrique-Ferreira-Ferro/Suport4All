package com.io.Suport4All.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository repositoryDepart;
	
	
	public Optional<DepartamentoEntity> findDepartById(Long id) {
		Optional<DepartamentoEntity> depart = repositoryDepart.findById(id);
		if(!depart.isPresent()) {
			throw new RuntimeException("Departamento não encontrado");
		}else {
			return repositoryDepart.findById(id);
		}
		
	}
	
	
	public DepartamentoEntity createDepart(DepartamentoEntity departamento) {
		if(departamento.getNomeDepart().isBlank()) {
			throw new RuntimeException("Não deixe o nome do departamento em branco");
		}
		return repositoryDepart.save(departamento);
	}
	
	
	
	public DepartamentoEntity updateDepartById(DepartamentoEntity departamento, Long id){
		Optional<DepartamentoEntity> depart = repositoryDepart.findById(id);
		
		if(depart.isPresent()) {
			DepartamentoEntity modDepart = depart.get();
			modDepart.setNomeDepart(departamento.getNomeDepart());
			modDepart.setUsers(departamento.getUsers());
			return repositoryDepart.save(modDepart);
		}else {
			throw new RuntimeException("Erro ao tentar atualizar departamento");
		}
		
	}

	public String deleteDepart(Long id) {
		Optional<DepartamentoEntity> depart = repositoryDepart.findById(id);
		if(depart.isPresent()) {
			repositoryDepart.deleteById(id);
			return "Departamento "+ depart.get().getNomeDepart()+ " deletado com sucesso!";
		}else {
			throw new RuntimeException("Erro ao tentar deletar o departamento");
		}
		
	}
	
	
}
