package com.mattvoget.sarlacc.dtos;

import org.apache.commons.codec.digest.DigestUtils;

public class AuthRequest {
	
	private String username;
	private String password;
	private String pin;
	
	public AuthRequest() {
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = new String(DigestUtils.shaHex(password));
	}
	
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) {
		this.pin = new String(DigestUtils.shaHex(pin));
	}

}
