package com.io.Suport4All.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.SenhasGeraisEntity;

@Repository
public interface SenhasGeraisRepository extends JpaRepository<SenhasGeraisEntity, Long>{

	List<SenhasGeraisEntity> findByOrigem(String titulo);
	
	
}
