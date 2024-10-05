package com.io.Suport4All.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.ChamadoEntity;

@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long>{
	
	List<ChamadoEntity> findByData(LocalDate data);
	
	List<ChamadoEntity> findByTitulo(String titulo);
 	
}
