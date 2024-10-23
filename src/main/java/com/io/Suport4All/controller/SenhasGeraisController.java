package com.io.Suport4All.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.SenhasGeraisDto;
import com.io.Suport4All.infra.security.TokenService;
import com.io.Suport4All.repository.DepartamentoRepository;
import com.io.Suport4All.repository.UsuarioRepository;
import com.io.Suport4All.service.SenhasGeraisService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/senhas")
@RequiredArgsConstructor
public class SenhasGeraisController {

	@Autowired
	private SenhasGeraisService senhasService;
	
	@PostMapping("/create")
	public SenhasGeraisDto createSenha(@RequestBody SenhasGeraisDto senhaDto) {
		return senhasService.createSenha(senhaDto);
	}

	@PutMapping("/update/{id}")
	public SenhasGeraisDto updateSenha(@RequestBody SenhasGeraisDto senhaDto, @PathVariable Long id) {
		return senhasService.updateSenha(senhaDto, id);
	}

	@GetMapping("/{id}")
	public SenhasGeraisDto findById(@PathVariable Long id) {
		return senhasService.findSenhaById(id);
	}

	@GetMapping
	public List<SenhasGeraisDto> findAll(){
		return senhasService.findAllSenhas();
	}

	@GetMapping("/origem")
	public List<SenhasGeraisDto> findAllByOrigem(@RequestBody SenhasGeraisDto senhaDto){
		return senhasService.findAllByOrigem(senhaDto.getOrigem());
	}

	@DeleteMapping("/{id}")
	public String deleteSenhaById(@PathVariable Long id) {
		return senhasService.deleteSenhaById(id);
	}

	
	
}
