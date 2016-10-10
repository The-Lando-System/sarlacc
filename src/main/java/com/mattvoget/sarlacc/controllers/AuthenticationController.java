package com.mattvoget.sarlacc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mattvoget.sarlacc.dtos.AuthResponse;
import com.mattvoget.sarlacc.repositories.UserRepository;
import com.mattvoget.sarlacc.utils.UserActivityLogger;

@Controller
public class AuthenticationController extends ErrorHandlingController {
	
	private Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private UserActivityLogger userLogger;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value="login-success", method=RequestMethod.GET)
	public @ResponseBody AuthResponse loginSuccess() {
		return new AuthResponse(true,"Successfully authenticated");
	}

}
