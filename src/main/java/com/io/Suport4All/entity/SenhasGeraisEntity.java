package com.io.Suport4All.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Senhas")
@Data
@NoArgsConstructor
public class SenhasGeraisEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String origem;
	@NotBlank
	private String login;
	@Email
	private String email;
	@NotBlank
	private String senha;
	
	private String descricao;
	
	public SenhasGeraisEntity(Long id, @NotBlank String origem, @NotBlank String login, @Email String email,
			@NotBlank String senha, String descricao) {
		super();
		this.id = id;
		this.origem = origem;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.descricao = descricao;
	}
	
	
	
	
	
}
