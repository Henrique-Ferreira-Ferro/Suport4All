package com.io.Suport4All.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Email {
	
	private String destinatario;
	private String assunto;
	private String mensagem;
}
