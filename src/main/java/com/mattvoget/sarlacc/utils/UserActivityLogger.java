package com.mattvoget.sarlacc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mattvoget.sarlacc.dtos.AuthRequest;

@Component
public class UserActivityLogger {
	
	private Logger log = LoggerFactory.getLogger(UserActivityLogger.class);

	@SuppressWarnings("rawtypes")
	public void logUserEvent(String username, Class fromClass, String message){
		log.info(String.format("User Event => event: %s, from class: %s, username: %s", message, fromClass.getName(), username));
	}
	
	public void logAuthentication(AuthRequest request, boolean isSuccessful){
		String authResult = isSuccessful ? "SUCCESS" : "FAILED";
		String message = String.format("Authentication Request => username: %s, result: %s", request.getUsername(), authResult);
		if (isSuccessful){
			log.info(message);
		} else {
			log.warn(message);
		}
	}
	
}
