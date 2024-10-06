package com.io.Suport4All.dto;

import com.io.Suport4All.entity.SenhasGeraisEntity;

import lombok.Data;

@Data
public class SenhasGeraisDto {

	private Long id;
	private String origem;
	private String login;
	private String email;
	private String senha;
	private String descricao;
	
	
	public SenhasGeraisDto() {
		
	}
	
	public SenhasGeraisDto(Long id, String origem, String login, String email, String senha, String descricao) {
		this.id = id;
		this.origem = origem;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.descricao = descricao;
	}
	
	
	public SenhasGeraisDto(SenhasGeraisEntity entity) {
		this.id = entity.getId();
		this.origem = entity.getOrigem();
		this.login = entity.getLogin();
		this.email = entity.getEmail();
		this.senha = entity.getSenha();
		this.descricao = entity.getDescricao();
	}
	
	
}
