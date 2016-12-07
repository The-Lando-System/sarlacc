package com.mattvoget.sarlacc.controllers;

import com.mattvoget.sarlacc.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mattvoget.sarlacc.repositories.UserRepository;
import com.mattvoget.sarlacc.utils.UserActivityLogger;

import java.util.List;

@Controller
@RequestMapping(value="account")
public class AccountController extends ErrorHandlingController {
	
	private Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private UserActivityLogger userLogger;
	
	@Autowired
	private UserRepository userRepo;

	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public void createAccount(@RequestBody User newAccount) {
		userRepo.createAccount(newAccount);
	}

	@PreAuthorize("@securityHelper.isAdmin()")
	@RequestMapping(value="/", method=RequestMethod.PUT)
	@ResponseBody
	public void editAccount(@RequestBody User accountToEdit) {
		userRepo.save(accountToEdit);
	}

	@PreAuthorize("@securityHelper.isAdmin()")
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@PreAuthorize("@securityHelper.isAdmin()")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable String id) {
		return userRepo.findOne(id);
	}

	@PreAuthorize("@securityHelper.isAdmin()")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void deleteAccount(@PathVariable String id) {
		userRepo.delete(id);
	}
}
