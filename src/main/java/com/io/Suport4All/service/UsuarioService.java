package com.io.Suport4All.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.Suport4All.dto.UsuarioDTO;
import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.repository.DepartamentoRepository;
import com.io.Suport4All.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	//Encontrar por id
	public Optional<UsuarioEntity> findUserById(Long id){
		Optional<UsuarioEntity> user = usuarioRepository.findById(id);
		if(user.isPresent()) {
			return usuarioRepository.findById(id);
		}else {
			throw new RuntimeException("Usuario não encontrado");
		}
	}
	
	
	//Encontrar todos os usuarios
	
	public List<UsuarioEntity> findAllUsers(){
		return usuarioRepository.findAll();
	}
	
	
	//Criar um usuario
	public UsuarioDTO createUser(UsuarioDTO user) {
		Long departId = user.getDepartamento();
		
		Optional<DepartamentoEntity> departament = departamentoRepository.findById(departId);
		
		if(departament.isEmpty()) {
			throw new RuntimeException("O departamento não pode estar vaziu!");
		}
		
		UsuarioEntity usuarioEnti = new UsuarioEntity();
		usuarioEnti.setNome(user.getNome());
		usuarioEnti.setSenha(user.getSenha());
		usuarioEnti.setEmail(user.getEmail());
		usuarioEnti.setNivel(user.getNivel());
		usuarioEnti.setDepartamento(departament.get());
		usuarioEnti = usuarioRepository.save(usuarioEnti);
		return new UsuarioDTO(usuarioEnti);
		
	}
	
	
	
	//Atualizar um usuario
	public UsuarioEntity updateUser(UsuarioEntity user, Long id) {
		Optional<UsuarioEntity> userFind = usuarioRepository.findById(id);
		if(userFind.isPresent()) {
			UsuarioEntity userMod = userFind.get();
			userMod.setDepartamento(user.getDepartamento());
			userMod.setEmail(user.getEmail());
			userMod.setNivel(user.getNivel());
			userMod.setNome(user.getNome());
			userMod.setSenha(user.getSenha());
			return usuarioRepository.save(userMod);
		}else {
			throw new RuntimeException("Problema ao tentar atualizar usuario");
		}
		
	}
	
	//deletar um usuario
	public String deleteUser(Long id) {
		Optional<UsuarioEntity> user = usuarioRepository.findById(id);
		if(user.isPresent()) {
			 usuarioRepository.deleteById(id);
			 return "Usuario "+ user.get().getNome() + " deletado com sucesso";
		}else {
			throw new RuntimeException("Erro ao tentar deletar usuario");
		}
	}
	
	
	
	
}
