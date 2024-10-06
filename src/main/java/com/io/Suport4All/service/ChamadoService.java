package com.io.Suport4All.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.io.Suport4All.dto.ChamadoDTO;
import com.io.Suport4All.dto.ChamadoUpdateDTO;
import com.io.Suport4All.entity.ChamadoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.enums.ChamadoStatus;
import com.io.Suport4All.repository.ChamadoRepository;
import com.io.Suport4All.repository.UsuarioRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Value("${file.upload-dir}")
    private String uploadDir;

	// Encontrar um chamado por ID

	// Encontrar todos os chamados criados

	// Criar um chamado associando a um usuario

	public ChamadoDTO openChamado(ChamadoDTO chamadoDto) {
		Long UsuarioId = chamadoDto.getUsuarioId();

		Optional<UsuarioEntity> usuario = usuarioRepository.findById(UsuarioId);

		if (usuario.isEmpty()) {
			throw new RuntimeException("O chamado deve estar associado a um Usuario ou técnico");
		}
		
		if(usuario.get().getStatus().toString().equals("DESLIGADO")) {
			throw new RuntimeException("Usuario só pode abrir chamado se estiver ativado!");
		}
		
		ChamadoEntity chamadoEnt = new ChamadoEntity();
		chamadoEnt.setTitulo(chamadoDto.getTitulo());
		chamadoEnt.setDescricao(chamadoDto.getDescricao());
		chamadoEnt.setData(chamadoDto.getDate());
		// Aqui convertemos de valor numerico para enum
		// Necessario pesquisar
		chamadoEnt.setExtremidade(chamadoDto.getExtremidade());
		chamadoEnt.setStatus(chamadoDto.getStatus());
		chamadoEnt.setUsuario(usuario.get());
		MultipartFile anexo = chamadoDto.getAnexo();
        if (anexo != null && !anexo.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + anexo.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            try {
				Files.write(filePath, anexo.getBytes());
				chamadoEnt.setAnexo(filePath.toString());
			} catch (IOException e) {
				throw new RuntimeException("Erro ao salvar o arquivo: "+ e.getMessage());
			}   
        }
		chamadoEnt = chamadoRepository.save(chamadoEnt);
		return new ChamadoDTO(chamadoEnt);
	}
	// Atualizar um chamado associando a um usuario e passando o id do chamado
	// Nota, não deve ser possivel alterar nada além dos status do
	//chamado, e um usuario comum não consegue alterar os status

	public ChamadoDTO updateChamado(ChamadoUpdateDTO chamadoDto ,Long UsuarioId) {
		
		Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(UsuarioId);
		if(!usuarioEntity.isPresent()) {
			throw new RuntimeException("Não foi possível encontrar nenhum usuario");
		}
		
		if(usuarioEntity.get().getStatus().toString().equals("DESLIGADO")) {
			throw new RuntimeException("Usuario só pode editar chamado se estiver ativado!");
		}
		
		Optional<ChamadoEntity> chamadoEnt = chamadoRepository.findById(chamadoDto.getId());
		if(!chamadoEnt.isPresent()) {
			throw new RuntimeException("O chamado não deve estar vaziu!");
		}
		UsuarioEntity userFind = usuarioEntity.get();
		
		if(chamadoEnt.get().getStatus().toString().equals("FECHADO")) {
			throw new RuntimeException("Não é possivel editar um chamado Fechado!");
		}	
		
		if(!userFind.getRole().toString().equals("ADMIN")) {
			throw new RuntimeException("Somente os Tecnicos podem editar os Chamados!");
		}
		ChamadoEntity chamadoMod =  chamadoEnt.get();
		chamadoMod.setStatus(chamadoDto.getStatus());
		chamadoMod = chamadoRepository.save(chamadoMod);
		return new ChamadoDTO(chamadoMod);
	}
	
	// Listar todos os chamados
	
	public List<ChamadoDTO> findAll() {
		List<ChamadoEntity> chamadoEnt = chamadoRepository.findAll();
		List<ChamadoDTO> chamadoDto = new ArrayList<>();
		
		if(chamadoEnt.isEmpty()) {
			throw new RuntimeException("Não há chamados abertos no momento!");
		}
		
		for (ChamadoEntity chamadoE : chamadoEnt) {
			chamadoDto.add(new ChamadoDTO(chamadoE));
		}
		
		return chamadoDto;
		
	}
	
	
	public List<ChamadoDTO> findByDate(LocalDate date){
		
		List<ChamadoEntity> chamadosEnt = chamadoRepository.findByData(date);
		
		List<ChamadoDTO> chamadoDto = new ArrayList<>();
		
		if(chamadosEnt.isEmpty()) {
			throw new RuntimeException("Nenhum chamado encontrado nessa data!"+ date);
		}
		
		for (ChamadoEntity chamaFind : chamadosEnt) {
			chamadoDto.add(new ChamadoDTO(chamaFind));
		}
		
		return chamadoDto;
		
	}
	
	//Find by id
	
	public ChamadoDTO findById(Long id){
		
		ChamadoEntity chamado = chamadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o chamado!"));
		
		return new ChamadoDTO(chamado);
	}
	
	
	//Find by titulo
	
	public List<ChamadoDTO> findByTitulo(String titulo){
		
		List<ChamadoEntity> chamadoEnt = chamadoRepository.findByTitulo(titulo);
		
		List<ChamadoDTO> chamadoDto = new ArrayList<>();
		
		if(chamadoEnt.isEmpty()) {
			throw new RuntimeException("Nenhum chamado encontrado com esse titulo! " + titulo);
		}
		
		for(ChamadoEntity chamaFind: chamadoEnt) {
			chamadoDto.add(new ChamadoDTO(chamaFind));
		}
		
		return chamadoDto;
		
	}
	
	
	// Find By status
	
	public List<ChamadoDTO> findByStatus(ChamadoStatus status){
		
		List<ChamadoEntity> chamadoEnt = chamadoRepository.findByStatus(status);
		List<ChamadoDTO> chamadoDto = new ArrayList<>();
		
		if(chamadoEnt.isEmpty()) {
			throw new RuntimeException("Nenhum chamado encontrado com o status: "+ status);
		}
		
		for(ChamadoEntity chamaFind: chamadoEnt) {
			chamadoDto.add(new ChamadoDTO(chamaFind));
		}
		return chamadoDto;
		
	}
	
	
	
}
