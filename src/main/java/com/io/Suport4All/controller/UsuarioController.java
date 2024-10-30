package com.io.Suport4All.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
@RestController
@CrossOrigin("*")
@RequestMapping("usuario")
@RequiredArgsConstructor
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
	
	@PutMapping("/update/status/{id}")
	public UsuarioDTO updateStatusUser(@RequestBody UsuarioDTO user, @PathVariable Long id) {
		return usuarioService.updateStatusUser(user, id);
	}
	

	@GetMapping("/list/departamento")
	public List<UsuarioDTOAnexo> findAllUsersByDepart(@RequestBody UsuarioDTO user) {
		return usuarioService.findAllUsersByDepart(user.getDepartamentoNome());
	}
	
	@GetMapping("/find/email")
	public UsuarioDTO findUserByEmail(@RequestBody UsuarioDTO user){
		return usuarioService.findUserByEmail(user.getEmail());
	}

	
	//O código abaixo foi gerado pelo chatgpt
	//Eu como autor do projeto tive dificuldade de implementar essa funcionalidade
	//Por isso estou escrevendo aqui para revisar esse trecho e aprender 
	//o que foi feito!
	@GetMapping("/find/photo/{id}")
	public ResponseEntity<Resource> findImageUser(@PathVariable Long id){
		// Tenta detectar o tipo de conteúdo da imagem
	    Resource imageResource = usuarioService.findImageUser(id);

	    String contentType = "application/octet-stream"; // Tipo padrão, caso não seja uma imagem
	    try {
	        Path path = Paths.get(imageResource.getURI());
	        contentType = Files.probeContentType(path);
	    } catch (IOException ex) {
	        System.out.println("Erro ao determinar o tipo de conteúdo da imagem: " + ex.getMessage());
	    }

	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType(contentType))
	            .body(imageResource);
	}

	
}
