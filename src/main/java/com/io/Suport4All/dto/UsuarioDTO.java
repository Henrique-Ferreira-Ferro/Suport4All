package com.io.Suport4All.dto;

import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.enums.UserRole;

public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String senha;
	private String email;
	private UserRole nivel;
	private Long departamentoId;
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Long id, String nome, String senha, String email, UserRole nivel, Long departamentoId) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.nivel = nivel;
		this.departamentoId = departamentoId;
	}
	
	public UsuarioDTO(UsuarioEntity user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.senha = user.getSenha();
		this.email = user.getEmail();
		this.nivel = user.getNivel();
		this.departamentoId = user.getDepartamento().getId();
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getNivel() {
		return nivel;
	}

	public void setNivel(UserRole nivel) {
		this.nivel = nivel;
	}

	public Long getDepartamento() {
		return departamentoId;
	}
	
	public void setDepartamento(Long departamento) {
		this.departamentoId = departamento;
	}
	
	
	
	
}
