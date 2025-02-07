package com.io.Suport4All.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ForgotPassword {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fpid;

	
	 private String token;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationTime;
	
	//Colocar a classe que faz referencia ao seu usuário no sistema
	@OneToOne
	private UsuarioEntity user;
}
