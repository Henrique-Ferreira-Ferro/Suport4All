package com.io.Suport4All.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.ChamadoDTO;
import com.io.Suport4All.dto.ChamadoDTOAnexo;
import com.io.Suport4All.dto.ChamadoUpdateDTO;
import com.io.Suport4All.service.ChamadoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/chamado")
@RequiredArgsConstructor
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

	@GetMapping("/{id}")
	public ChamadoDTOAnexo findById(@PathVariable Long id){
		return chamadoService.findById(id);
	}
	
	@GetMapping
	public List<ChamadoDTOAnexo> findAll() {
		return chamadoService.findAll();
	}
	
	@GetMapping("/user/list/{id}")
	public List<ChamadoDTOAnexo> findAllChamadoByIdUser(@PathVariable Long id){
		return chamadoService.findAllChamadoByIdUser(id);
	}


	@GetMapping("/date")
	public List<ChamadoDTO> findByDate(@RequestBody ChamadoDTO chamadoDTO) {
		return chamadoService.findByDate(chamadoDTO.getDate());
	}

	@GetMapping("/titulo")
	public List<ChamadoDTO> findByTitulo(@RequestBody ChamadoDTO chamadoDTO){
		return chamadoService.findByTitulo(chamadoDTO.getTitulo());
	}

	@GetMapping("/status")
	public List<ChamadoDTO> findByStatus(@RequestBody ChamadoDTO chamadoDTO){
		return chamadoService.findByStatus(chamadoDTO.getStatus());
	}
	
	@GetMapping("/status/aberto")
	public int countStatusAberto() {
		return chamadoService.countStatusAberto();
	}
	
	@GetMapping("/status/andamento")
	public int countStatusEmAndamento() {
		return chamadoService.countStatusEmAndamento();
	}
	
	@GetMapping("/status/fechado")
	public int countStatusFechado() {
		return chamadoService.countStatusFechado();
	}
	
}
