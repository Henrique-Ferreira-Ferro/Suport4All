package com.io.Suport4All.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.Suport4All.dto.UsuarioDTO;
import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.enums.UserRole;
import com.io.Suport4All.repository.DepartamentoRepository;
import com.io.Suport4All.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	// Encontrar por id
	public UsuarioDTO findUserById(Long id) {

		UsuarioEntity user = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

		return new UsuarioDTO(user);

	}

	// Encontrar todos os usuarios

	public List<UsuarioDTO> findAllUsers() {
		List<UsuarioEntity> users = usuarioRepository.findAll();
		List<UsuarioDTO> userDTOs = new ArrayList<>();

		for (UsuarioEntity user : users) {
			userDTOs.add(new UsuarioDTO(user));
		}

		return userDTOs;
	}

	// Criar um usuario
	public UsuarioDTO createUser(UsuarioDTO user) {
		Long departId = user.getDepartamentoId();

		Optional<DepartamentoEntity> departament = departamentoRepository.findById(departId);

		if (departament.isEmpty()) {
			throw new RuntimeException("O departamento não pode estar vaziu!");
		}

		// Maneira mais eficiente de lançar uma exception, caso ocorra um erro!

		/*
		 * DepartamentoEntity departamento = departamentoRepository.findAll(departId)
		 * .orElseThrow(() -> new
		 * RuntimeException("O departamento não pode estar vaziu"))
		 */
		
		UsuarioEntity usuarioEnti = new UsuarioEntity();
		usuarioEnti.setNome(user.getNome());
		usuarioEnti.setSenha(user.getSenha());
		usuarioEnti.setEmail(user.getEmail());
		usuarioEnti.setRole(user.getRole());
		usuarioEnti.setDepartamento(departament.get());
		usuarioEnti = usuarioRepository.save(usuarioEnti);
		return new UsuarioDTO(usuarioEnti);

	}

	// Atualizar um usuario
	public UsuarioDTO updateUser(UsuarioDTO user, Long id) {
		UsuarioEntity userFind = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

		userFind.setDepartamento(departamentoRepository.findById(user.getDepartamentoId())
				.orElseThrow(() -> new RuntimeException("Departamento não encontrado!")));

		userFind.setEmail(user.getEmail());
		userFind.setRole(user.getRole());
		userFind.setNome(user.getNome());
		userFind.setSenha(user.getSenha());
		return new UsuarioDTO(usuarioRepository.save(userFind));

	}

	// deletar um usuario
	public String deleteUser(Long id) {
		Optional<UsuarioEntity> user = usuarioRepository.findById(id);
		if (user.isPresent()) {
			usuarioRepository.deleteById(id);
			return "Usuario " + user.get().getNome() + " deletado com sucesso";
		} else {
			throw new RuntimeException("Erro ao tentar deletar usuario");
		}
	}

}
