package com.io.Suport4All.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Departamento")
public class DepartamentoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Column(name = "nome")
	private String nomeDepart;
	
	//json back reference evita loop infinito
	
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<UsuarioEntity> users;
	
	
	public DepartamentoEntity() {
		
	}


	public DepartamentoEntity(long id, @NotBlank String nomeDepart, List<UsuarioEntity> users) {
		super();
		this.id = id;
		this.nomeDepart = nomeDepart;
		this.users = users;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNomeDepart() {
		return nomeDepart;
	}


	public void setNomeDepart(String nomeDepart) {
		this.nomeDepart = nomeDepart;
	}


	public List<UsuarioEntity> getUsers() {
		return users;
	}


	public void setUsers(List<UsuarioEntity> users) {
		this.users = users;
	}


	
	
	
	
	
}
