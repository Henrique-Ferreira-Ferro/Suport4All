package com.io.Suport4All.controller;

import java.util.List;
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

import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.service.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public Optional<UsuarioEntity> findUserById(@PathVariable Long id){
		return usuarioService.findUserById(id);
	}
	
	@GetMapping
	public List<UsuarioEntity> findAllUsers(){
		return usuarioService.findAllUsers();
	}

	@PostMapping("/create")
	public UsuarioEntity createUser(@RequestBody UsuarioEntity user) {
		return usuarioService.createUser(user);
	}

	
	@PutMapping("/update/{id}")
	public UsuarioEntity updateUser(@RequestBody UsuarioEntity user, @PathVariable Long id) {
		return usuarioService.updateUser(user, id);
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		return usuarioService.deleteUser(id);
	}

	
	
	
}
