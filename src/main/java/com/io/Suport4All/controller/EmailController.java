package com.io.Suport4All.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.entity.Email;
import com.io.Suport4All.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService service;
	
	@PostMapping("/send")
	public String enviarEmail(@RequestBody Email email) {
		return service.enviarEmail(email);
	}

	
	
}
