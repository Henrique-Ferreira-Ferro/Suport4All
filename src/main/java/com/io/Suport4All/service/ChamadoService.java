package com.io.Suport4All.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.io.Suport4All.dto.ChamadoDTO;
import com.io.Suport4All.entity.ChamadoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.enums.Extremidade;
import com.io.Suport4All.repository.ChamadoRepository;
import com.io.Suport4All.repository.UsuarioRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Value("${spring.servlet.multipart.location}")
	private String raiz;

	@Value("${contato.disco.diretorio-fotos}")
	private String diretorioFotos;

	// Encontrar um chamado por ID

	// Encontrar todos os chamados criados

	// Criar um chamado associando a um usuario

	public ChamadoDTO createChamado(ChamadoDTO chamadoDto, MultipartFile arquivo) {
		Long UsuarioId = chamadoDto.getUsuarioId();

		Optional<UsuarioEntity> usuario = usuarioRepository.findById(UsuarioId);

		if (usuario.isEmpty()) {
			throw new RuntimeException("O chamado deve estar associado a um Usuario ou técnico");
		}

		if (arquivo != null && !arquivo.isEmpty()) {
			System.out.println("Entrou no if");
			salvarArquivo(diretorioFotos, arquivo);
			chamadoDto.setAnexo(arquivo.getOriginalFilename());
			System.out.println(arquivo.getOriginalFilename());
		}
		
		ChamadoEntity chamadoEnt = new ChamadoEntity();
		chamadoEnt.setTitulo(chamadoDto.getTitulo());
		chamadoEnt.setDescricao(chamadoDto.getDescricao());
		chamadoEnt.setData(chamadoDto.getDate());
		// Aqui convertemos de valor numerico para enum
		// Necessario pesquisar
		chamadoEnt.setExtremidade(Extremidade.values()[chamadoDto.getExtremidade()]);
		chamadoEnt.setUsuario(usuario.get());
		chamadoEnt = chamadoRepository.save(chamadoEnt);
		return new ChamadoDTO(chamadoEnt);

	}

	private void salvarArquivo(String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao tentar salvar o arquivo: " + e.getMessage());
		}

	}

	// Atualizar um chamado associando a um usuario e passando o id do chamado
	// Nota, não deve ser possivel alterar nada além dos status do chamado

	// Deletar chamado? Acredito que isso não deve ser implementado segundo os
	// requisitos do sistema que será feito
	// Por questões de auditoria

}
