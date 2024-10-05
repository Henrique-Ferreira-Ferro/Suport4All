package com.io.Suport4All.enums;

public enum ChamadoStatus {
	ABERTO("Aberto"),
	EM_ANDAMENTO("Em Andamento"),
	FECHADO ("Fechado");
	
	private String status;
	
	 ChamadoStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	
	
	
}
