package com.io.Suport4All.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.UsuarioDTO;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public UsuarioDTO findUserById(@PathVariable Long id){
		return usuarioService.findUserById(id);
	}
	
	@GetMapping
	public List<UsuarioDTO> findAllUsers(){
		return usuarioService.findAllUsers();
	}

	@PostMapping("/create")
	public UsuarioDTO createUser(@RequestBody UsuarioDTO user) {
		return usuarioService.createUser(user);
	}

	@PostMapping("/upload")
	public UsuarioDTO uploadUser(@ModelAttribute @Valid UsuarioDTO user) {
		return usuarioService.uploadPhoto(user);
	}
	
	
	@PutMapping("/update/{id}")
	public UsuarioDTO updateUser(@RequestBody UsuarioDTO user, @PathVariable Long id) {
		return usuarioService.updateUser(user, id);
	}

	@GetMapping("/list/departamento")
	public List<UsuarioDTO> findAllUsersByDepart(@RequestBody UsuarioDTO user) {
		return usuarioService.findAllUsersByDepart(user.getDepartamentoNome());
	}
	
	
}
