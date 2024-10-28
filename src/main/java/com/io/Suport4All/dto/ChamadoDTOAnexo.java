package com.io.Suport4All.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.Suport4All.entity.ChamadoEntity;
import com.io.Suport4All.enums.ChamadoStatus;
import com.io.Suport4All.enums.Extremidade;

import lombok.Data;

@Data
public class ChamadoDTOAnexo {
	private Long id;
	private String titulo;
	private String descricao;
	private Extremidade extremidade;
	@JsonFormat(pattern =  "dd/MM/yyyy")
	private String date;
	// Não faz sentido pedir todo o usuario certo? Pois estamos associando apenas ao id do mesmo
	private Long usuarioId;
    private String anexo;
    
    private ChamadoStatus status;
    
	public ChamadoDTOAnexo() {
		
	}

	public ChamadoDTOAnexo(Long id, String titulo, String descricao,ChamadoStatus status ,Extremidade extremidade, String date, Long usuarioId,
			String anexo) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.extremidade = extremidade;
		this.date = date;
		this.usuarioId = usuarioId;
		this.anexo = anexo;
	}
	
	public ChamadoDTOAnexo(ChamadoEntity chamadoEntity) {

		this.id = chamadoEntity.getId();
		this.titulo = chamadoEntity.getTitulo();
		this.descricao = chamadoEntity.getDescricao();
		this.status = chamadoEntity.getStatus();
		this.extremidade = chamadoEntity.getExtremidade() ;
		this.date = chamadoEntity.getData();
		this.usuarioId = chamadoEntity.getUsuario().getId();
		this.anexo = chamadoEntity.getAnexo();
	}
}
