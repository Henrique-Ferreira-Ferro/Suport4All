package com.io.Suport4All.exceptions;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApiError {
	
	private int statusCode;
	private String message;
	private LocalDate date;
	public ApiError(int statusCode, String message, LocalDate date) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.date = date;
	}	
	
	
	
	
}
