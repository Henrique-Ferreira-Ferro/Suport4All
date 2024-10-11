package com.io.Suport4All.controller;

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

import com.io.Suport4All.dto.UsuarioDTO;
import com.io.Suport4All.dto.UsuarioDTOAnexo;
import com.io.Suport4All.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public UsuarioDTOAnexo findUserById(@PathVariable Long id){
		return usuarioService.findUserById(id);
	}
	
	@GetMapping
	public List<UsuarioDTOAnexo> findAllUsers(){
		return usuarioService.findAllUsers();
	}

	@PostMapping("/create")
	public UsuarioDTO createUser(@RequestBody UsuarioDTO user) {
		return usuarioService.createUser(user);
	}

	@PostMapping("/upload/{id}")
	public UsuarioDTO uploadUser(@ModelAttribute @Valid UsuarioDTO user, @PathVariable Long id) {
		return usuarioService.uploadPhoto(user.getAnexo(), id);
	}
	
	
	@PutMapping("/update/{id}")
	public UsuarioDTO updateUser(@RequestBody UsuarioDTO user, @PathVariable Long id) {
		return usuarioService.updateUser(user, id);
	}

	@GetMapping("/list/departamento")
	public List<UsuarioDTOAnexo> findAllUsersByDepart(@RequestBody UsuarioDTO user) {
		return usuarioService.findAllUsersByDepart(user.getDepartamentoNome());
	}
	
	
}
