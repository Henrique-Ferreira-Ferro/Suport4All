package com.io.Suport4All.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.Suport4All.entity.ChamadoEntity;
import com.io.Suport4All.enums.ChamadoStatus;
import com.io.Suport4All.enums.Extremidade;

public class ChamadoDTO {
	
	private Long id;
	private String titulo;
	private String descricao;
	private Extremidade extremidade;
	@JsonFormat(pattern =  "dd/MM/yyyy")
	private String date;
	// Não faz sentido pedir todo o usuario certo? Pois estamos associando apenas ao id do mesmo
	private Long usuarioId;
    private MultipartFile anexo;
    
    private ChamadoStatus status;
    
	public ChamadoDTO() {
		
	}

	public ChamadoDTO(Long id, String titulo, String descricao,ChamadoStatus status ,Extremidade extremidade, String date, Long usuarioId,
			MultipartFile anexo) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.extremidade = extremidade;
		this.date = date;
		this.usuarioId = usuarioId;
		this.anexo = anexo;
	}
	
	public ChamadoDTO(ChamadoEntity chamadoEntity) {

		this.id = chamadoEntity.getId();
		this.titulo = chamadoEntity.getTitulo();
		this.descricao = chamadoEntity.getDescricao();
		this.status = chamadoEntity.getStatus();
		this.extremidade = chamadoEntity.getExtremidade() ;
		this.date = chamadoEntity.getData();
		this.usuarioId = chamadoEntity.getUsuario().getId();
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public MultipartFile getAnexo() {
		return anexo;
	}

	public void setAnexo(MultipartFile anexo) {
		this.anexo = anexo;
	}

	public ChamadoStatus getStatus() {
		return status;
	}

	public void setStatus(ChamadoStatus status) {
		this.status = status;
	}
	
	
	
	
}
