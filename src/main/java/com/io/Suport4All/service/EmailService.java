package com.io.Suport4All.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.io.Suport4All.entity.Email;

@Service
public class EmailService {

	
	private final JavaMailSender javaMailSender;
	
	public EmailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	
	
	@Value("${spring.mail.username}")
	private String remetente;
	
	public String enviarEmail(Email email) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(remetente);
			simpleMailMessage.setTo(email.getDestinatario());
			simpleMailMessage.setSubject(email.getAssunto());
			simpleMailMessage.setText(email.getMensagem());
			javaMailSender.send(simpleMailMessage);
			return "Email enviado";
		}catch(Exception e) {
			return "Erro ao enviar email "+ e.getLocalizedMessage();
		}
	}
	
	
}
