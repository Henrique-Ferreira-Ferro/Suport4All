package com.io.Suport4All.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.io.Suport4All.repository.DepartamentoRepository;

@RunWith(MockitoJUnitRunner.class)
public class DepartamentoServiceTest {

	@Mock
	private DepartamentoRepository departamentoRepository;
	
	@InjectMocks
	private DepartamentoService departamentoService;
	
	
	
	
}
