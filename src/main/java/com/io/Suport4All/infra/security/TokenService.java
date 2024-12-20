package com.io.Suport4All.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.io.Suport4All.entity.UsuarioEntity;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(UsuarioEntity user) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			String role = user.getRole().toString().toUpperCase();
			
			String status = user.getStatus().toString().toUpperCase();
			
			String id = user.getId().toString().toUpperCase();
			
			String token = JWT.create()
					.withIssuer("login-auth-api")
					.withSubject(user.getEmail())
					.withClaim("roles", Collections.singletonList(role))
					.withClaim("status", Collections.singletonList(status))
					.withClaim("id", Collections.singletonList(id))
					.withExpiresAt(this.generateExpirationDate())
					.sign(algorithm);
			return token;
		}catch(JWTCreationException exception) {
			throw new RuntimeException("Error while authenticating");
		}
		
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("login-auth-api")
					.build()
					.verify(token)
					.getSubject();
					
		}catch(JWTVerificationException exception) {
			return null;
		}
	}
	
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
	
	
}
