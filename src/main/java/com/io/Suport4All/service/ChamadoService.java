package com.io.Suport4All.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.io.Suport4All.dto.ChamadoDTO;
import com.io.Suport4All.dto.ChamadoDTOAnexo;
import com.io.Suport4All.dto.ChamadoUpdateDTO;
import com.io.Suport4All.entity.ChamadoEntity;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.enums.ChamadoStatus;
import com.io.Suport4All.exceptions.BadRequestException;
import com.io.Suport4All.exceptions.ForbiddenException;
import com.io.Suport4All.exceptions.NotFound;
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
		
		
		//Geração de data automaticamente
		
		Date dataAtual = new Date();
		String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataAtual);
		
		
		if (chamadoDto.getUsuarioId() == null) {
			throw new BadRequestException("O chamado deve estar associado a um Usuario ou técnico");
		}
		
		if(chamadoDto.getUsuarioId() <= 0) {
			throw new BadRequestException("O id do usuario ou tecnico precisa ser positivo e inteiro!");
		}
		
		Long UsuarioId = chamadoDto.getUsuarioId();

		Optional<UsuarioEntity> usuario = usuarioRepository.findById(UsuarioId);
		
		if(!usuario.isPresent()) {
			throw new NotFound("Ops, esse usuario não existe no nosso sistema :(");
		}

		if(usuario.get().getStatus().toString().equals("DESLIGADO")) {
			throw new ForbiddenException("Usuario só pode abrir chamado se estiver ativado!");
		}
		
		ChamadoEntity chamadoEnt = new ChamadoEntity();
		if(chamadoDto.getTitulo().isBlank() || chamadoDto.getTitulo() == null) {
			throw new BadRequestException("O titulo do chamado não pode ser vaziu!");
		}
		chamadoEnt.setTitulo(chamadoDto.getTitulo());
		chamadoEnt.setDescricao(chamadoDto.getDescricao());
		
		//Data gerada automaticamente
		chamadoEnt.setData(dataFormatada);
		// Aqui convertemos de valor numerico para enum
		// Necessario pesquisar
		if(chamadoDto.getExtremidade().toString().isBlank() || chamadoDto.getExtremidade() == null) {
			throw new BadRequestException("O chamado deve conter o nivel de Extremidade!");
		}
		chamadoEnt.setExtremidade(chamadoDto.getExtremidade());
		chamadoEnt.setStatus(ChamadoStatus.ABERTO);
		chamadoEnt.setUsuario(usuario.get());
		MultipartFile anexo = chamadoDto.getAnexo();
        if (anexo != null && !anexo.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + anexo.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            try {
				Files.write(filePath, anexo.getBytes());
				chamadoEnt.setAnexo(filePath.toString());
			} catch (IOException e) {
				throw new BadRequestException("Erro ao salvar o arquivo: "+ e.getMessage());
			}   
        }
		chamadoEnt = chamadoRepository.save(chamadoEnt);
		return new ChamadoDTO(chamadoEnt);
	}
	// Atualizar um chamado associando a um usuario e passando o id do chamado
	// Nota, não deve ser possivel alterar nada além dos status do
	//chamado, e um usuario comum não consegue alterar os status

	public ChamadoDTO updateChamado(ChamadoUpdateDTO chamadoDto ,Long UsuarioId) {
		
		if(UsuarioId == null) {
			throw new BadRequestException("Insira o id de um usuario!");
		}
		if(UsuarioId <= 0) {
			throw new BadRequestException("Insira um id valido! Apenas numeros!");
		}
		
		Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(UsuarioId);
		if(!usuarioEntity.isPresent()) {
			throw new NotFound("Não foi possível encontrar nenhum usuario");
		}
		
		if(usuarioEntity.get().getStatus().toString().equals("DESLIGADO")) {
			throw new ForbiddenException("Usuario só pode editar chamado se estiver ativado!");
		}
		
		Optional<ChamadoEntity> chamadoEnt = chamadoRepository.findById(chamadoDto.getId());
		if(!chamadoEnt.isPresent()) {
			throw new NotFound("O chamado não foi encontrado!");
		}
		UsuarioEntity userFind = usuarioEntity.get();
		
		if(chamadoEnt.get().getStatus().toString().equals("FECHADO")) {
			throw new ForbiddenException("Não é possivel editar um chamado Fechado!");
		}	
		
//		if(!userFind.getRole().toString().equals("ADMIN")) {
//			throw new ForbiddenException("Somente os Tecnicos podem editar os Chamados!");
//		}
		ChamadoEntity chamadoMod =  chamadoEnt.get();
		chamadoMod.setStatus(chamadoDto.getStatus());
		chamadoMod = chamadoRepository.save(chamadoMod);
		return new ChamadoDTO(chamadoMod);
	}
	
	// Listar todos os chamados
	
	public List<ChamadoDTOAnexo> findAll() {
		List<ChamadoEntity> chamadoEnt = chamadoRepository.findAll();
		List<ChamadoDTOAnexo> chamadoDto = new ArrayList<>();
		
		if(chamadoEnt.isEmpty()) {
			throw new NotFound("Não há chamados abertos no momento!");
		}
		
		for (ChamadoEntity chamadoE : chamadoEnt) {
			chamadoDto.add(new ChamadoDTOAnexo(chamadoE));
		}
		
		return chamadoDto;
		
	}
	
	//Listar todos os chamados de um determinado usuario 
	public List<ChamadoDTOAnexo> findAllChamadoByIdUser(Long idUser){
		List<ChamadoEntity> chamadoEnt = chamadoRepository.findAllChamadoByIdUser(idUser);

		List<ChamadoDTOAnexo> chamadoDto = new ArrayList<>();
		
		if(chamadoEnt.isEmpty()) {
			throw new NotFound("Não há chamados abertos no momento!");
		}
		
		for(ChamadoEntity chamadoE: chamadoEnt) {
			chamadoDto.add(new ChamadoDTOAnexo(chamadoE));
		}
		
		return chamadoDto;
	}
	
	
	
	public List<ChamadoDTO> findByDate(String date){
		
		List<ChamadoEntity> chamadosEnt = chamadoRepository.findByData(date);
		
		List<ChamadoDTO> chamadoDto = new ArrayList<>();
		
		if(chamadosEnt.isEmpty()) {
			throw new NotFound("Nenhum chamado encontrado nessa data!"+ date);
		}
		
		for (ChamadoEntity chamaFind : chamadosEnt) {
			chamadoDto.add(new ChamadoDTO(chamaFind));
		}
		
		return chamadoDto;
		
	}
	
	//Find by id
	
	public ChamadoDTOAnexo findById(Long id){
		
		ChamadoEntity chamado = chamadoRepository.findById(id).orElseThrow(() -> new NotFound("Não foi possivel encontrar o chamado!"));
		
		return new ChamadoDTOAnexo(chamado);
	}
	
	
	//Find by titulo
	
	public List<ChamadoDTO> findByTitulo(String titulo){
		
		List<ChamadoEntity> chamadoEnt = chamadoRepository.findByTitulo(titulo);
		
		List<ChamadoDTO> chamadoDto = new ArrayList<>();
		
		if(chamadoEnt.isEmpty()) {
			throw new NotFound("Nenhum chamado encontrado com esse titulo! " + titulo);
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
			throw new NotFound("Nenhum chamado encontrado com o status: "+ status);
		}
		
		for(ChamadoEntity chamaFind: chamadoEnt) {
			chamadoDto.add(new ChamadoDTO(chamaFind));
		}
		return chamadoDto;
		
	}
	
	//Download do anexo enviado 
	
	public ResponseEntity<InputStreamResource> downloadAnexo(Long chamadoId) {
	    // Localiza o chamado pelo ID e lança exceção se não encontrado
	    ChamadoEntity chamado = chamadoRepository.findById(chamadoId)
	        .orElseThrow(() -> new NotFound("Chamado não encontrado para o ID: " + chamadoId));

	    // Verifica se o anexo existe no chamado
	    if (chamado.getAnexo() == null || chamado.getAnexo().isEmpty()) {
	        throw new NotFound("Nenhum anexo encontrado para este chamado.");
	    }

	    // Define o caminho do arquivo
	    Path filePath = Paths.get(chamado.getAnexo());
	    try {
	        InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath.toFile()));

	        // Configura cabeçalhos de resposta para o download
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filePath.getFileName().toString());

	        return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(Files.size(filePath))
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);

	    } catch (FileNotFoundException e) {
	        throw new NotFound("Arquivo não encontrado: " + e.getMessage());
	    } catch (IOException e) {
	        throw new BadRequestException("Erro ao baixar o arquivo: " + e.getMessage());
	    }
	}
	
	
	public int countStatusAberto() {
		return chamadoRepository.countStatusAberto();
	}
	
	public int countStatusEmAndamento() {
		return chamadoRepository.countStatusAndamento();
	}
	
	public int countStatusFechado() {
		return chamadoRepository.countStatusFechado();
	}
	
	//Contagem dos chamados para usuarios especificos 
	
	
	public int countStatusAbertoUser(Long id) {
		return chamadoRepository.countStatusAbertoUser(id);
	}
	
	
	public int countStatusAndamentoUser(Long id) {
		return chamadoRepository.countStatusAndamentoUser(id);
	}
	
	public int countStatusFechadoUser(Long id) {
		return chamadoRepository.countStatusFechadoUser(id);
	}
	
}
