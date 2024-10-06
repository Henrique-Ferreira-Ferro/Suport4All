package com.io.Suport4All.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{
	
	
	// findByName
	List<UsuarioEntity> findByNome(String nome);
	
	@Query(value = "SELECT u.* FROM usuario u JOIN departamento d ON u.id_departamento = d.id WHERE d.nome = :nomeDepart", nativeQuery = true)
	List<UsuarioEntity> findByNomeDepartamento(@Param("nomeDepart") String nomeDepart);
	
	
}
