package com.io.Suport4All.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.Suport4All.dto.DepartamentoDTO;
import com.io.Suport4All.entity.DepartamentoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.exceptions.BadRequestException;
import com.io.Suport4All.exceptions.ForbiddenException;
import com.io.Suport4All.exceptions.NotFound;
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
				.orElseThrow(() -> new NotFound("Departamento não encontrado"));
		return new DepartamentoDTO(depart);
	}

	public List<DepartamentoDTO> findAllDepart() {
		List<DepartamentoEntity> departamentos = repositoryDepart.findAll();
		
		if(departamentos.isEmpty()) {
			throw new NotFound("Não há departamentos cadastrados no momento!");
		}
		
		List<DepartamentoDTO> departamentosDTOs = new ArrayList<DepartamentoDTO>();

		for (DepartamentoEntity departamento : departamentos) {
			departamentosDTOs.add(new DepartamentoDTO(departamento));
		}
		return departamentosDTOs;

	}

	public DepartamentoDTO createDepart(DepartamentoDTO departamentoDTO) {

		if (departamentoDTO.getNomeDepart().isBlank() || departamentoDTO.getNomeDepart() == null) {
			throw new BadRequestException("Não deixe o nome do departamento em branco!");
		}
		
		List<DepartamentoEntity> allDeparts = repositoryDepart.findAll();
		
		for (DepartamentoEntity departamentoEntity : allDeparts) {
			if(departamentoDTO.getNomeDepart().equals(departamentoEntity.getNomeDepart())) {
				throw new ForbiddenException("Não gere departamentos com mesmo nome!");
			}
		}
		
		DepartamentoEntity departamento = new DepartamentoEntity();
		departamento.setNomeDepart(departamentoDTO.getNomeDepart());
		departamento.setDescricao(departamentoDTO.getDescricao());
		DepartamentoEntity savedDepartamento = repositoryDepart.save(departamento);
		return new DepartamentoDTO(savedDepartamento);

	}

	public DepartamentoDTO updateDepartById(DepartamentoDTO departamento, Long id) {

		DepartamentoEntity depart = repositoryDepart.findById(id)
				.orElseThrow(() -> new NotFound("Erro ao tentar encontrar o departamento!"));
		
		depart.setDescricao(departamento.getDescricao());

		DepartamentoEntity updatedDepartamento = repositoryDepart.save(depart);
		return new DepartamentoDTO(updatedDepartamento);

	}

	public List<DepartamentoDTO> findByNome(String nome){
		
		if(nome.isBlank() || nome == null) {
			throw new BadRequestException("O campo nome precisa ser digitado!");
		}
		
		List<DepartamentoEntity> departamentoEnti = repositoryDepart.findByNomeDepartLike(nome);
		
		if(departamentoEnti.isEmpty()) {
			throw new NotFound("Nenhum departamento encontrado");
		}
		
		List<DepartamentoDTO> departamentoDto = new ArrayList<>();
		
		for (DepartamentoEntity departE : departamentoEnti) {
			departamentoDto.add(new DepartamentoDTO(departE));
		}
		
		return departamentoDto;
		
	}

	
	
}
