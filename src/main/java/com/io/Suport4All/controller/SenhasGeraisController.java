package com.io.Suport4All.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.service.SenhasGeraisService;

@RestController
@RequestMapping("/senhas")
public class SenhasGeraisController {

	@Autowired
	private SenhasGeraisService senhasService;
	
	
	
	
}
