package com.io.Suport4All.enums;

public enum UserStatus {
	ATIVO("Ativo"),
	DESLIGADO("Desligado");
	
	private String status;
	
	UserStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	
}
