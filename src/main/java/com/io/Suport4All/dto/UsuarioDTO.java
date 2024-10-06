package com.io.Suport4All.dto;

import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.enums.UserRole;
import com.io.Suport4All.enums.UserStatus;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String senha;
	private String email;
	private UserRole role;
	private String departamentoNome;
	private UserStatus status;

	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Long id, String nome, String senha, String email, UserRole role, String departamentoNome, UserStatus status) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.role = role;
		this.departamentoNome = departamentoNome;
		this.status = status;
	}
	
	public UsuarioDTO(UsuarioEntity user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.senha = user.getSenha();
		this.email = user.getEmail();
		this.role = user.getRole();
		this.departamentoNome = user.getDepartamento().getNomeDepart();
		this.status = user.getStatus();
	}
	
	
	
}
