package com.mattvoget.sarlacc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mattvoget.sarlacc.dtos.ExceptionResponse;
import com.mattvoget.sarlacc.exceptions.AuthenticationException;

public class ErrorHandlingController {

	@ResponseStatus(value=HttpStatus.FORBIDDEN)
	@ExceptionHandler(AuthenticationException.class)
	public @ResponseBody ExceptionResponse failedToAuthenticate(AuthenticationException e) {
		return new ExceptionResponse(e.getMessage(), e.getClass().getName());
	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public @ResponseBody ExceptionResponse unknownException(Exception e) {
		return new ExceptionResponse(e.getMessage(), e.getClass().getName());
	}
	
}
