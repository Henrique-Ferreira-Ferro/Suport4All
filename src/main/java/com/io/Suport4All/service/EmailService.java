package com.io.Suport4All.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.io.Suport4All.dto.MailBody;

@Service
public class EmailService {

	
	private final JavaMailSender javaMailSender;
	
	public EmailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	
	
	@Value("${spring.mail.username}")
	private String remetente;
	
	public String enviarEmail(MailBody mailBody) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(mailBody.getTo());
			simpleMailMessage.setFrom(remetente);
			simpleMailMessage.setSubject(mailBody.getSubject());
			simpleMailMessage.setText(mailBody.getText());
			
			javaMailSender.send(simpleMailMessage);
			return "Email enviado";
		}catch(Exception e) {
			return "Erro ao enviar email "+ e.getLocalizedMessage();
		}
	}

	
	
}
