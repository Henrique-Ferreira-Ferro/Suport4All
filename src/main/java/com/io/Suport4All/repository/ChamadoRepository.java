package com.io.Suport4All.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.ChamadoEntity;
import com.io.Suport4All.enums.ChamadoStatus;

@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long>{
	
	List<ChamadoEntity> findByData(String data);
	
	List<ChamadoEntity> findByTitulo(String titulo);
 
	List<ChamadoEntity> findByStatus(ChamadoStatus status);
	
	
}
