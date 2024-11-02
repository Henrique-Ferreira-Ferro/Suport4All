package com.io.Suport4All.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.ChamadoEntity;
import com.io.Suport4All.enums.ChamadoStatus;

@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long>{
	
	List<ChamadoEntity> findByData(String data);
	
	List<ChamadoEntity> findByTitulo(String titulo);
 
	List<ChamadoEntity> findByStatus(ChamadoStatus status);
	
	@Query(value = "SELECT count(status) from chamado WHERE status = 'ABERTO'", nativeQuery = true)
	int countStatusAberto();
	
	@Query(value = "SELECT count(status) from chamado WHERE status = 'EM_ANDAMENTO'", nativeQuery = true)
	int countStatusAndamento();
	
	@Query(value = "SELECT count(status) from chamado WHERE status = 'FECHADO'", nativeQuery = true)
	int countStatusFechado();
	
	@Query(value = "SELECT * FROM chamado WHERE usuario_id = ?1", nativeQuery = true)
	List<ChamadoEntity> findAllChamadoByIdUser(Long id);
	
	
}
