package com.io.Suport4All.controller;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.ChangePassword;
import com.io.Suport4All.dto.MailBody;
import com.io.Suport4All.entity.ForgotPassword;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.exceptions.BadRequestException;
import com.io.Suport4All.repository.ForgotPasswordRepository;
import com.io.Suport4All.repository.UsuarioRepository;
import com.io.Suport4All.service.EmailService;

@RestController
@RequestMapping("/forgotPassword")
@CrossOrigin("*")
public class ForgotPasswordController {

	private final UsuarioRepository userRepository;
	private final EmailService emailService;
	private final ForgotPasswordRepository forgotPasswordRepository;

	private final PasswordEncoder passwordEncoder;

	public ForgotPasswordController(UsuarioRepository userRepository, 
			EmailService emailService, ForgotPasswordRepository forgotPasswordRepository,PasswordEncoder  passwordEncoder) {
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.forgotPasswordRepository = forgotPasswordRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// Envio de email para verificar conta

	@PostMapping("/verifyMail/{email}")
	public ResponseEntity<String> verifyEmail(@PathVariable String email) {
		 UsuarioEntity user = userRepository.findByEmail(email)
		            .orElseThrow(() -> new BadRequestException("Invalid email"));

		    // Gerar token único para redefinição
		    String token = UUID.randomUUID().toString();

		    // Construir o e-mail
		    MailBody mailBody = MailBody.builder()
		            .to(email)
		            .text("Clique no link para redefinir sua senha: " +
		                    "http://localhost:5500/reset-password.html?token=" + token)
		            .subject("Redefinição de Senha")
		            .build();

		    // Salvar no banco com tempo de expiração
		    ForgotPassword forgotPassword = ForgotPassword.builder()
		            .token(token)
		            .expirationTime(new Date(System.currentTimeMillis() + 15 * 60 * 1000)) // 15 minutos
		            .user(user)
		            .build();

		    forgotPasswordRepository.save(forgotPassword);

		    // Enviar e-mail
		    emailService.enviarEmail(mailBody);

		    return ResponseEntity.ok("Email enviado com o link de redefinição de senha!");

	}
	
	@PostMapping("/reset")
	public ResponseEntity<Map<String, String>> validateToken(@RequestParam String token) {
	    ForgotPassword forgotPassword = forgotPasswordRepository.findByToken(token)
	            .orElseThrow(() -> new BadRequestException("Token inválido ou expirado!"));

	    if (forgotPassword.getExpirationTime().before(Date.from(Instant.now()))) {
	        forgotPasswordRepository.delete(forgotPassword);
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Token expirado!");
	        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	    }

	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Token válido! Você pode redefinir sua senha.");
	    return ResponseEntity.ok(response);
	}



//Para a troca da senha do usuário

	@PostMapping("/changePassword")
	public ResponseEntity<Map<String, String>> resetPassword(@RequestParam String token, @RequestBody ChangePassword changePassword) {
	    ForgotPassword forgotPassword = forgotPasswordRepository.findByToken(token)
	            .orElseThrow(() -> new BadRequestException("Token inválido ou expirado!"));

	    if (!Objects.equals(changePassword.getPassword(), changePassword.getRepeatPassword())) {
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "As senhas não coincidem!");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    UsuarioEntity user = forgotPassword.getUser();

	    // Criptografar a nova senha e atualizar no banco
	    String encodedPassword = passwordEncoder.encode(changePassword.getPassword());
	    user.setSenha(encodedPassword);
	    userRepository.save(user);

	    // Remover o token usado
	    forgotPasswordRepository.delete(forgotPassword);

	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Senha redefinida com sucesso!");
	    return ResponseEntity.ok(response);
	}


}
