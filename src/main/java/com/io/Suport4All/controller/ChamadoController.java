package com.io.Suport4All.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.io.Suport4All.dto.ChamadoDTO;
import com.io.Suport4All.service.ChamadoService;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {
	
	
	
	@Autowired
	private ChamadoService chamadoService;
	
	
	//Salvar/criar um chamado
	@PostMapping("/create")
	public ChamadoDTO createChamado
	(@RequestBody ChamadoDTO chamadoDto, @RequestParam("file") MultipartFile arquivo) {
		
		return chamadoService.createChamado(chamadoDto, arquivo);
	}
	
	
	
	
	
}
