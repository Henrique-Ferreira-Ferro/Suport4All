package com.io.Suport4All.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.SenhasGeraisDto;
import com.io.Suport4All.service.SenhasGeraisService;

@RestController
@RequestMapping("/senhas")
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

	
	
}
