package com.io.Suport4All.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.enums.UserRole;
import com.io.Suport4All.enums.UserStatus;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
	
	
	private String nome;
	private String senha;
	private String email;
	private DepartamentoEntity departamento;
	private UserRole role;
	private UserStatus status;
	
	
	
}
