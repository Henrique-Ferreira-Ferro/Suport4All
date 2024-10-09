package com.io.Suport4All.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.Suport4All.enums.ChamadoStatus;
import com.io.Suport4All.enums.Extremidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Chamado")
public class ChamadoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@NotNull
	private String titulo;
	private String descricao;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Extremidade extremidade;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ChamadoStatus status;
	
	@JsonFormat(pattern =  "dd/MM/yyyy")
	private LocalDate data;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private UsuarioEntity usuario;
	
	private String anexo;
	
	public ChamadoEntity() {
		
	}
	
	
	public ChamadoEntity(Long id, @NotBlank String titulo, String descricao, Extremidade extremidade, LocalDate data,
			UsuarioEntity usuario, String anexo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.extremidade = extremidade;
		this.data = data;
		this.usuario = usuario;
		this.anexo = anexo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Extremidade getExtremidade() {
		return extremidade;
	}

	public void setExtremidade(Extremidade extremidade) {
		this.extremidade = extremidade;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}


	public ChamadoStatus getStatus() {
		return status;
	}


	public void setStatus(ChamadoStatus status) {
		this.status = status;
	}
	
	
	
}
