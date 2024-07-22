package com.io.Suport4All.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.service.DepartamentoService;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService service;
	
	@GetMapping("/{id}")
	public Optional<DepartamentoEntity> findById(@PathVariable Long id){
		return service.findDepartById(id);
	}
	
	@PostMapping("/create")
	public DepartamentoEntity createDepart(@RequestBody DepartamentoEntity departamento) {
		return service.createDepart(departamento);
	}
	
	@PutMapping("/update/{id}")
	public DepartamentoEntity updateDepartById(@RequestBody DepartamentoEntity departamento, @PathVariable Long id) {
		return service.updateDepartById(departamento, id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteDepart(@PathVariable Long id) {
		return service.deleteDepart(id);
	}
	
}
