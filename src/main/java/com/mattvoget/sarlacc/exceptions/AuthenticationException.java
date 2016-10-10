package com.mattvoget.sarlacc.exceptions;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
		super();
	}
	
	public AuthenticationException(String message, Throwable e){
		super(message,e);
	}
	
	public AuthenticationException(String message){
		super(message);
	}
	
	public AuthenticationException(Throwable e){
		super(e);
	}
	
}
