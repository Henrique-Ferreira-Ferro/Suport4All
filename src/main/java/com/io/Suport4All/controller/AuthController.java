package com.io.Suport4All.controller;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.LoginRequestDTO;
import com.io.Suport4All.dto.RegisterRequestDTO;
import com.io.Suport4All.dto.ResponseDTO;
import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.enums.UserRole;
import com.io.Suport4All.enums.UserStatus;
import com.io.Suport4All.exceptions.BadRequestException;
import com.io.Suport4All.exceptions.ForbiddenException;
import com.io.Suport4All.exceptions.NotFound;
import com.io.Suport4All.infra.security.TokenService;
import com.io.Suport4All.repository.DepartamentoRepository;
import com.io.Suport4All.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
	
	private final UsuarioRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	private final DepartamentoRepository departamentoRepository;
	
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDTO body) {
		UsuarioEntity user = this.repository.findByEmail(body.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
		if(passwordEncoder.matches(body.getSenha(), user.getSenha())){
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token));
		}
		throw new ForbiddenException("E-mail ou senha incorreta!");
	}
	
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
		
		Optional<UsuarioEntity> user = this.repository.findByEmail(body.getEmail());
		
		
		
		if(user.isEmpty()) {
			UsuarioEntity newUser = new UsuarioEntity();
			newUser.setNome(body.getNome());
			newUser.setEmail(body.getEmail());
			newUser.setSenha(passwordEncoder.encode(body.getSenha()));
			newUser.setRole(UserRole.USER);
			
			
			DepartamentoEntity departamento = departamentoRepository.findByNomeDepart(body.getDepartamento())
					.orElseThrow(() -> new NotFound("Não foi possivel encontrar o departamento com nome: "+ body.getDepartamento()));
					
			newUser.setDepartamento(departamento);
			newUser.setStatus(UserStatus.ATIVO);
			this.repository.save(newUser);
			
			String token = this.tokenService.generateToken(newUser);
			return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));
		}
		throw new BadRequestException("Não foi possivel registrar o usuario!");
	}
	
	
	
}
