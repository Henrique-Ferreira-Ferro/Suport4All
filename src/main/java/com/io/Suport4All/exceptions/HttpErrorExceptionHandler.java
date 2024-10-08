package com.io.Suport4All.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class HttpErrorExceptionHandler {
	
	private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String message){
		var error = new ApiError(status.value(), message, LocalDate.now());
		
		return ResponseEntity.status(status).body(error);
	}
	
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiError> badRequest(BadRequestException exception){
		return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ApiError> forbiddenRequest(ForbiddenException exception){
		return buildErrorResponse(HttpStatus.FORBIDDEN, exception.getMessage());
	}
	
	@ExceptionHandler(NotFound.class)
	public ResponseEntity<ApiError> notFound(NotFound exception){
		return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
	}
	
}
