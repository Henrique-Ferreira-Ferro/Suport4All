package com.io.Suport4All.enums;

public enum Extremidade {
	SIMPLES("Simples"),
	MEDIA("Media"),
	COMPLEXA("Complexa"),
	URGENTE("Urgente");
	
	private String extremidade;
	
	Extremidade(String extremidade){
		this.extremidade = extremidade;
	}

	public String getExtremidade() {
		return extremidade;
	}
	
	
	
}
