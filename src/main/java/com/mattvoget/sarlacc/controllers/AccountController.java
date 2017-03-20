package com.mattvoget.sarlacc.controllers;

import com.mattvoget.sarlacc.exceptions.AuthenticationException;
import com.mattvoget.sarlacc.models.User;
import com.mattvoget.sarlacc.security.SecurityHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mattvoget.sarlacc.repositories.UserRepository;
import com.mattvoget.sarlacc.utils.UserActivityLogger;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value="account")
public class AccountController extends ErrorHandlingController {
	
	private Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private UserActivityLogger userLogger;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

    @Autowired
    private SecurityHelper securityHelper;

	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public void createAccount(@RequestBody User newAccount) {
		userRepo.createAccount(newAccount);
	}

	@PreAuthorize("@securityHelper.isAdmin()")
	@RequestMapping(value="/", method=RequestMethod.PUT)
	@ResponseBody
	public void editAccount(@RequestBody User accountToEdit) {
		if (!StringUtils.isBlank(accountToEdit.getPassword())){
			accountToEdit.setPassword(encoder.encode(accountToEdit.getPassword()));
		} else {
			accountToEdit.setPassword(userRepo.findOne(accountToEdit.getId()).getPassword());
		}
		userRepo.save(accountToEdit);
	}

	@PreAuthorize("@securityHelper.hasAccess()")
	@RequestMapping(value="/me/", method=RequestMethod.PUT)
	@ResponseBody
	public User editMyAccount(Principal authUser, @RequestBody User accountToEdit) {

        if (!StringUtils.equals(accountToEdit.getId(),securityHelper.getUser().getId())){
            throw new AuthenticationException("Could not verify the account ID during account update!");
        }

		// Users can't update their own role. Set it to what it already was
		accountToEdit.setRole(userRepo.findOne(accountToEdit.getId()).getRole());

		if (!StringUtils.isBlank(accountToEdit.getPassword())){
			accountToEdit.setPassword(encoder.encode(accountToEdit.getPassword()));
		} else {
			accountToEdit.setPassword(userRepo.findOne(accountToEdit.getId()).getPassword());
		}

		return userRepo.save(accountToEdit);
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
