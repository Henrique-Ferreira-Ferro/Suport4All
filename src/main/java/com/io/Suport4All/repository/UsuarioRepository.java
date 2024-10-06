package com.io.Suport4All.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{
	
	
	// findByName
	List<UsuarioEntity> findByNome(String nome);
	
	
	
	
	
}
