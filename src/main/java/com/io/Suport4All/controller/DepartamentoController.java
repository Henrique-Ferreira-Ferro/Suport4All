package com.io.Suport4All.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.DepartamentoDTO;
import com.io.Suport4All.service.DepartamentoService;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService service;
	
	@GetMapping("/{id}")
	public DepartamentoDTO findById(@PathVariable Long id){
		return service.findDepartById(id);
	}
	
	@GetMapping
	public List<DepartamentoDTO> findAllDepart() {
		return service.findAllDepart();
	}
	
	@PostMapping("/create")
	public DepartamentoDTO createDepart(@RequestBody DepartamentoDTO departamento) {
		return service.createDepart(departamento);
	}
	
	@PutMapping("/update/{id}")
	public DepartamentoDTO updateDepartById(@RequestBody DepartamentoDTO departamento, @PathVariable Long id) {
		return service.updateDepartById(departamento, id);
	}
	
	
}
