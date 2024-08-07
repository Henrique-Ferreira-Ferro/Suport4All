package com.io.Suport4All.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.io.Suport4All.entity.ChamadoEntity;
import com.io.Suport4All.enums.Extremidade;

public class ChamadoDTO {
	
	private Long id;
	private String titulo;
	private String descricao;
	private Extremidade extremidade;
	private LocalDateTime date;
	// Não faz sentido pedir todo o usuario certo? Pois estamos associando apenas ao id do mesmo
	private Long usuarioId;
	private String anexo;
	
	public ChamadoDTO() {
		
	}

	public ChamadoDTO(Long id, String titulo, String descricao, Extremidade extremidade, LocalDateTime date, Long usuarioId,
			String anexo) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.extremidade = extremidade;
		this.date = date;
		this.usuarioId = usuarioId;
		this.anexo = anexo;
	}
	
	public ChamadoDTO(ChamadoEntity chamadoEntity) {

		this.id = chamadoEntity.getId();
		this.titulo = chamadoEntity.getTitulo();
		this.descricao = chamadoEntity.getDescricao();
		this.extremidade = chamadoEntity.getExtremidade();
		this.date = chamadoEntity.getData();
		this.usuarioId = chamadoEntity.getUsuario().getId();
		this.anexo = chamadoEntity.getAnexo();
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	
	
	
	
	
	
	
	
	
}
