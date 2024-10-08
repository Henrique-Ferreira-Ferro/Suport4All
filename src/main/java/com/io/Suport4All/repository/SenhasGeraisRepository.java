package com.io.Suport4All.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.SenhasGeraisEntity;

@Repository
public interface SenhasGeraisRepository extends JpaRepository<SenhasGeraisEntity, Long>{
	
	@Query(value = "SELECT * FROM Senhas WHERE origem LIKE :prefix%", nativeQuery = true)
	List<SenhasGeraisEntity> findByOrigem(@Param("prefix") String prefix);
	
	
}
