package com.io.Suport4All.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.ChamadoDTO;
import com.io.Suport4All.service.ChamadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;

	// Salvar/criar um chamado
	@PostMapping(value = "/create")
	public ChamadoDTO createChamado(@ModelAttribute @Valid ChamadoDTO chamadoDTO) {

		return chamadoService.createChamado(chamadoDTO);
	}

}
