package com.io.Suport4All.infra.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.exceptions.NotFound;
import com.io.Suport4All.repository.UsuarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioEntity user = repository.findByEmail(email).orElseThrow(() -> new NotFound("Usuario não encontrado!"));
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString().toUpperCase());
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getSenha(), 
				Collections.singleton(authority));
		
	}
	
	
	
	
}
