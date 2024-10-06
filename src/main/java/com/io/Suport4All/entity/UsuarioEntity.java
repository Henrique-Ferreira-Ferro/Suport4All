package com.io.Suport4All.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.io.Suport4All.enums.UserStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Usuario")
public class UsuarioEntity extends PessoaEntity{
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_departamento")
	private DepartamentoEntity departamento;
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<ChamadoEntity> chamados;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserStatus status;
	
	
	//Um usuario pode abrir muitos chamados
	
	
	
	public UsuarioEntity() {
		
	}



	public DepartamentoEntity getDepartamento() {
		return departamento;
	}


	public void setDepartamento(DepartamentoEntity departamento) {
		this.departamento = departamento;
	}



	public UserStatus getStatus() {
		return status;
	}



	public void setStatus(UserStatus status) {
		this.status = status;
	}


	public List<ChamadoEntity> getChamados() {
		return chamados;
	}


	public void setChamados(List<ChamadoEntity> chamados) {
		this.chamados = chamados;
	}

	
}
