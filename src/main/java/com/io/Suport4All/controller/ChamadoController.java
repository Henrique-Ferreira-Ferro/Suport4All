package com.io.Suport4All.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.ChamadoDTO;
import com.io.Suport4All.dto.ChamadoUpdateDTO;
import com.io.Suport4All.service.ChamadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;

	// Salvar/criar um chamado
	@PostMapping("/create")
	public ChamadoDTO openChamado(@ModelAttribute @Valid ChamadoDTO chamadoDTO) {

		return chamadoService.openChamado(chamadoDTO);
	}

	@PutMapping("/update/{id}")
	public ChamadoDTO editChamado(@RequestBody @Valid ChamadoUpdateDTO chamadoDTO, @PathVariable Long id) {
		return chamadoService.updateChamado(chamadoDTO, id);
	}

	@GetMapping
	public List<ChamadoDTO> findAll() {
		return chamadoService.findAll();
	}

	@GetMapping("/date")
	public List<ChamadoDTO> findByDate(@RequestBody ChamadoDTO chamadoDTO) {
		LocalDate data = chamadoDTO.getDate(); // Spring faz a conversão automática de String para LocalDate
		return chamadoService.findByDate(data);
	}

	@GetMapping("/titulo")
	public List<ChamadoDTO> findByTitulo(@RequestBody ChamadoDTO chamadoDTO){
		return chamadoService.findByTitulo(chamadoDTO.getTitulo());
	}

	
	
}
