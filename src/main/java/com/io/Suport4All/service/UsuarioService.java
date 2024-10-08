package com.io.Suport4All.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.io.Suport4All.dto.UsuarioDTO;
import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.enums.UserStatus;
import com.io.Suport4All.exceptions.BadRequestException;
import com.io.Suport4All.repository.DepartamentoRepository;
import com.io.Suport4All.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Value("{file.upload-userP}")
	private String uploadDirPerfil;
	
	
	public UsuarioDTO uploadPhoto(UsuarioDTO user) {
		MultipartFile anexo = user.getAnexo();
		UsuarioEntity userEntity = new UsuarioEntity();
		if(anexo != null && !anexo.isEmpty()) {
			String fileName = System.currentTimeMillis() + "_" + anexo.getOriginalFilename();
			Path filePath = Paths.get(uploadDirPerfil + fileName);
			
			try {
				Files.write(filePath, anexo.getBytes());
				userEntity.setAnexo(filePath.toString());
			}catch(IOException e) {
				throw new BadRequestException("Não foi possivel carregar a foto do usuario!"+ e.getMessage());
			}
			
		}
		userEntity = usuarioRepository.save(userEntity);
		return new UsuarioDTO(userEntity);
	}

	// Criar um usuario
	public UsuarioDTO createUser(UsuarioDTO user) {
		String departNome = user.getDepartamentoNome();

		Optional<DepartamentoEntity> departament = departamentoRepository.findByNomeDepart(departNome);

		if (departament.isEmpty()) {
	        throw new RuntimeException("Departamento '" + departNome + "' não encontrado!");
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
		usuarioEnti.setStatus(UserStatus.ATIVO);
		usuarioEnti.setRole(user.getRole());
		usuarioEnti.setDepartamento(departament.get());
		usuarioEnti = usuarioRepository.save(usuarioEnti);
		return new UsuarioDTO(usuarioEnti);

	}

	// Atualizar um usuario
	public UsuarioDTO updateUser(UsuarioDTO user, Long id) {
		UsuarioEntity userFind = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

		userFind.setDepartamento(departamentoRepository.findByNomeDepart(user.getDepartamentoNome())
				.orElseThrow(() -> new RuntimeException("Departamento não encontrado!")));

		userFind.setEmail(user.getEmail());
		userFind.setStatus(user.getStatus());
		userFind.setRole(user.getRole());
		userFind.setNome(user.getNome());
		userFind.setSenha(user.getSenha());
		return new UsuarioDTO(usuarioRepository.save(userFind));

	}


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
	
	
	//Encontrar todos os usuarios de um determinado departamento
		public List<UsuarioDTO> findAllUsersByDepart(String nomeDepart){
			List<UsuarioEntity> users = usuarioRepository.findByNomeDepartamento(nomeDepart);
			List<UsuarioDTO> userDTO = new ArrayList<>();
			
			for(UsuarioEntity user: users) {
				userDTO.add(new UsuarioDTO(user));
			}
			return userDTO;
		}
	
		
	
}
