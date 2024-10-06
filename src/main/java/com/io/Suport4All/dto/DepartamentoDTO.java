package com.io.Suport4All.dto;

import com.io.Suport4All.entity.DepartamentoEntity;

public class DepartamentoDTO {
	
	private Long id;
	private String nomeDepart;
	
	public DepartamentoDTO() {
		
	}
	
	public DepartamentoDTO(Long id, String nomeDepart) {
		super();
		this.id = id;
		this.nomeDepart = nomeDepart;
		
	}
	
	public DepartamentoDTO(DepartamentoEntity departamento) {
		this.id = departamento.getId();
		this.nomeDepart = departamento.getNomeDepart();
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nomeDepart;
	}

	public void setNome(String nome) {
		this.nomeDepart = nome;
	}


	
	

	
}
