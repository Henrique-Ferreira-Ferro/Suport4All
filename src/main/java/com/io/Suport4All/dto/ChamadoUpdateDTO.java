package com.io.Suport4All.dto;

import com.io.Suport4All.entity.ChamadoEntity;
import com.io.Suport4All.enums.ChamadoStatus;

import lombok.Data;

@Data
public class ChamadoUpdateDTO {
	private Long id;               
    private ChamadoStatus status;  

    public ChamadoUpdateDTO() {}

    public ChamadoUpdateDTO(Long id, ChamadoStatus status) {
        this.id = id;
        this.status = status;
    }
    
    public ChamadoUpdateDTO(ChamadoEntity entity) {
    	this.id = entity.getId();
    	this.status = entity.getStatus();
    }


}
