package com.io.Suport4All.dto;

import com.io.Suport4All.entity.DepartamentoEntity;

public class DepartamentoDTO {
	
	private Long id;
	private String nomeDepart;
	private String descricao;

	
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

	public String getNomeDepart() {
		return nomeDepart;
	}

	public void setNomeDepart(String nomeDepart) {
		this.nomeDepart = nomeDepart;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	
	

	
}
