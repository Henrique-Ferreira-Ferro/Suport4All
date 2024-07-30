package com.io.Suport4All.dto;

import com.io.Suport4All.entity.DepartamentoEntity;

public class DepartamentoDTO {
	
	private Long id;
	private String nome;
	
	
	public DepartamentoDTO() {
		
	}
	
	public DepartamentoDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public DepartamentoDTO(DepartamentoEntity departamento) {
		this.id = departamento.getId();
		this.nome = departamento.getNomeDepart();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	
}
