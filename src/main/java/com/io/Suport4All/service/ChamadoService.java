package com.io.Suport4All.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	
	private static String caminhoImagem = "C:\\Users\\Henrique\\Documents\\Java\\Suport4All\\src\\main\\resources\\images";

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	// Encontrar um chamado por ID

	// Encontrar todos os chamados criados

	// Criar um chamado associando a um usuario
	//Coisas que provavelmente vão ocorrer de erro:
	//Erro com a data
	//Erro com o anexo
	//Erro com o usuario que está sendo passado
	public ChamadoDTO createChamado(ChamadoDTO chamadoDto,  MultipartFile arquivo) {
		Long UsuarioId = chamadoDto.getUsuarioId();
		
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(UsuarioId);
		
		if(usuario.isEmpty()) {
			throw new RuntimeException("O chamado deve estar associado a um Usuario ou técnico");
		}
		
		try {
			if(arquivo != null && !arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagem, String.valueOf(arquivo.getOriginalFilename()));
				Files.write(caminho, bytes);
				String nomeImage = String.valueOf(arquivo.getOriginalFilename());
				chamadoDto.setAnexo(nomeImage);
				
			}
		}catch(IOException e){
			throw new RuntimeException("Erro ao tentar salvar o arquivo"+ e.getMessage());
		}
		
		ChamadoEntity chamadoEnt = new ChamadoEntity();
		chamadoEnt.setTitulo(chamadoDto.getTitulo());
		chamadoEnt.setDescricao(chamadoDto.getDescricao());
		chamadoEnt.setData(chamadoDto.getDate());
		//Aqui convertemos de valor numerico para enum
		//Necessario pesquisar
		chamadoEnt.setExtremidade(Extremidade.values()[chamadoDto.getExtremidade()]);
		chamadoEnt.setUsuario(usuario.get());
		chamadoEnt = chamadoRepository.save(chamadoEnt);
		return new ChamadoDTO(chamadoEnt);
	
	}
	

	
	
	//Atualizar um chamado associando a um usuario e passando o id do chamado
	//Nota, não deve ser possivel alterar nada além dos status do chamado
	
	
	//Deletar chamado? Acredito que isso não deve ser implementado segundo os requisitos do sistema que será feito
	//Por questões de auditoria
	

	
	
}
