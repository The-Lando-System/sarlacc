package com.mattvoget.sarlacc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserActivityLogger {
	
	private Logger log = LoggerFactory.getLogger(UserActivityLogger.class);

	@SuppressWarnings("rawtypes")
	public void logUserEvent(String username, Class fromClass, String message){
		log.info(String.format("User Event => event: %s, from class: %s, username: %s", message, fromClass.getName(), username));
	}
		
}
