package com.io.Suport4All.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.Suport4All.dto.DepartamentoDTO;
import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.repository.DepartamentoRepository;
import com.io.Suport4All.repository.UsuarioRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repositoryDepart;

	//Será usado no futuro
	@Autowired
	private UsuarioRepository repositoryUser;

	public DepartamentoDTO findDepartById(Long id) {
		DepartamentoEntity depart = repositoryDepart.findById(id)
				.orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
		return new DepartamentoDTO(depart);
	}

	public List<DepartamentoDTO> findAllDepart() {
		List<DepartamentoEntity> departamentos = repositoryDepart.findAll();
		List<DepartamentoDTO> departamentosDTOs = new ArrayList<DepartamentoDTO>();

		for (DepartamentoEntity departamento : departamentos) {
			departamentosDTOs.add(new DepartamentoDTO(departamento));
		}
		return departamentosDTOs;

	}

	public DepartamentoDTO createDepart(DepartamentoDTO departamentoDTO) {

		if (departamentoDTO.getNomeDepart().isBlank()) {
			throw new RuntimeException("Não deixe o nome do departamento em branco!");
		}

		DepartamentoEntity departamento = new DepartamentoEntity();
		departamento.setNomeDepart(departamentoDTO.getNomeDepart());
		departamento.setDescricao(departamentoDTO.getDescricao());
		DepartamentoEntity savedDepartamento = repositoryDepart.save(departamento);
		return new DepartamentoDTO(savedDepartamento);

	}

	public DepartamentoDTO updateDepartById(DepartamentoDTO departamento, Long id) {

		DepartamentoEntity depart = repositoryDepart.findById(id)
				.orElseThrow(() -> new RuntimeException("Erro ao tentar encontrar o departamento!"));
		
		depart.setDescricao(departamento.getDescricao());

		DepartamentoEntity updatedDepartamento = repositoryDepart.save(depart);
		return new DepartamentoDTO(updatedDepartamento);

	}

	

}
