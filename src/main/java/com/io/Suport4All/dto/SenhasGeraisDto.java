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
	
	
	public SenhasGeraisDto(SenhasGeraisEntity entity) {
		this.origem = entity.getOrigem();
		this.login = entity.getLogin();
		this.email = entity.getEmail();
		this.senha = entity.getSenha();
		this.descricao = entity.getDescricao();
	}
	
	
}
