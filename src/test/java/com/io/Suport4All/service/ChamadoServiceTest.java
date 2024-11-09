package com.io.Suport4All.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.io.Suport4All.repository.ChamadoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ChamadoServiceTest {

	@Mock
	private ChamadoRepository chamadoRepository;
	
	@InjectMocks
	private ChamadoService chamadoService;
	
	
	
	
}
