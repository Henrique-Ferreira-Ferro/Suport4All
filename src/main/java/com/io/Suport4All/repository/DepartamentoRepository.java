package com.io.Suport4All.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.DepartamentoEntity;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Long>{
    
	Optional<DepartamentoEntity> findByNomeDepart(String nomeDepart);
	
	@Query(value = "SELECT * FROM departamento WHERE nome LIKE :prefix%", nativeQuery = true)
	List<DepartamentoEntity> findByNomeDepartLike(@Param("prefix") String prefix);
	
}
