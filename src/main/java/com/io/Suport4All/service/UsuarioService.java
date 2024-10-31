package com.io.Suport4All.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.io.Suport4All.dto.UsuarioDTO;
import com.io.Suport4All.dto.UsuarioDTOAnexo;
import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.enums.UserStatus;
import com.io.Suport4All.exceptions.BadRequestException;
import com.io.Suport4All.exceptions.NotFound;
import com.io.Suport4All.repository.DepartamentoRepository;
import com.io.Suport4All.repository.UsuarioRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Value("${file.upload-userP}")
	private String uploadDirPerfil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public UsuarioDTO uploadPhoto(MultipartFile anexo, Long id) {
		Optional<UsuarioEntity> userEntity = usuarioRepository.findById(id);
		if(userEntity.isEmpty()) {
			throw new NotFound("Não foi possivel encontrar o usuario!");
		}
		UsuarioEntity userFind = userEntity.get();
		if(anexo != null && !anexo.isEmpty()) {
			String fileName = System.currentTimeMillis() + "_" + anexo.getOriginalFilename();
			Path filePath = Paths.get(uploadDirPerfil + fileName);
			
			try {
				Files.write(filePath, anexo.getBytes());
				userFind.setAnexo(filePath.toString());
			}catch(IOException e) {
				throw new BadRequestException("Não foi possivel carregar a foto do usuario!"+ e.getMessage());
			}
			
		}
		userFind = usuarioRepository.save(userFind);
		return new UsuarioDTO(userFind);
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
		
		String encoder = passwordEncoder.encode(user.getSenha());
		
		UsuarioEntity usuarioEnti = new UsuarioEntity();
		usuarioEnti.setNome(user.getNome());
		usuarioEnti.setSenha(encoder);
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
				.orElseThrow(() -> new NotFound("Usuario não encontrado!"));

		userFind.setDepartamento(departamentoRepository.findByNomeDepart(user.getDepartamentoNome())
				.orElseThrow(() -> new NotFound("Departamento não encontrado!")));
		
		
		userFind.setEmail(user.getEmail());
		userFind.setStatus(user.getStatus());
		userFind.setRole(user.getRole());
		userFind.setNome(user.getNome());
		return new UsuarioDTO(usuarioRepository.save(userFind));

	}
	
	public String updatePassword(UsuarioDTO user, Long id) {
		UsuarioEntity userFind = usuarioRepository.findById(id)
				.orElseThrow(() -> new NotFound("Usuario não encontrado!"));
		
		String encoder = passwordEncoder.encode(user.getSenha());
		
		userFind.setSenha(encoder);
		usuarioRepository.save(userFind);
		return "Senha modificada com sucesso!";
	}
	
	
	//Modificar o status do usuario
	public UsuarioDTO updateStatusUser(UsuarioDTO user, Long id) {
		UsuarioEntity userFind = usuarioRepository.findById(id).orElseThrow(() -> new NotFound("Usuario não encontrado!"));
		
		userFind.setStatus(user.getStatus());
		return new UsuarioDTO(usuarioRepository.save(userFind));
		
	}


	// Encontrar por id
		public UsuarioDTOAnexo findUserById(Long id) {

			UsuarioEntity user = usuarioRepository.findById(id)
					.orElseThrow(() -> new NotFound("Usuario não encontrado"));

			return new UsuarioDTOAnexo(user);

		}

		// Encontrar todos os usuarios

		public List<UsuarioDTOAnexo> findAllUsers() {
			List<UsuarioEntity> users = usuarioRepository.findAll();
			List<UsuarioDTOAnexo> userDTOs = new ArrayList<>();

			for (UsuarioEntity user : users) {
				userDTOs.add(new UsuarioDTOAnexo(user));
			}

			return userDTOs;
		}
	
	
		//Encontrar todos os usuarios de um determinado departamento
		public List<UsuarioDTOAnexo> findAllUsersByDepart(String nomeDepart){
			List<UsuarioEntity> users = usuarioRepository.findByNomeDepartamento(nomeDepart);
			List<UsuarioDTOAnexo> userDTO = new ArrayList<>();
			
			for(UsuarioEntity user: users) {
				userDTO.add(new UsuarioDTOAnexo(user));
			}
			return userDTO;
		}
	
		//Encontrar usuario por email
		public UsuarioDTO findUserByEmail(String email){
			Optional<UsuarioEntity> userFind = usuarioRepository.findByEmail(email);
			
			if(!userFind.isPresent()) {
				throw new NotFound("Usuario não encontrado");
			}
			UsuarioEntity user = userFind.get();
			return new UsuarioDTO(user);
		}
		
		
		//Notas do autor: Após o try o código foi gerado pelo chatGpt, por isso 
		//Será necessario uma revisão meticulosa do que foi feito, afim de se aprender
		//e replicar em outros projetos
		public Resource findImageUser(Long id) {
			 UsuarioEntity userF = usuarioRepository.findById(id)
			            .orElseThrow(() -> new NotFound("Ops, não encontramos nenhum usuário :("));
			    String filePath = userF.getAnexo(); // Caminho completo da imagem
			    
			    try {
			        Path path = Paths.get(filePath);
			        Resource resource = new UrlResource(path.toUri());
			        
			        if (resource.exists() && resource.isReadable()) {
			            return resource;
			        } else {
			            throw new RuntimeException("Erro: Imagem não encontrada ou não pode ser lida.");
			        }
			    } catch (MalformedURLException e) {
			        throw new RuntimeException("Erro ao carregar a imagem.", e);
			    } catch(NullPointerException f) {
			    	throw new NotFound("Usuario não possui foto");
			    }
		}
		
	
}
