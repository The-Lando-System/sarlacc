package com.mattvoget.sarlacc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mattvoget.sarlacc.models.AppRole;
import com.mattvoget.sarlacc.services.AppRoleService;

@Controller
@RequestMapping(value="app-role")
public class AppRoleController {

	@Autowired private AppRoleService appRoleSvc;
	
	@RequestMapping(value="/{username}/{appName}", method=RequestMethod.GET)
	@ResponseBody
	public AppRole getUserRoleForApp(@PathVariable String username, @PathVariable String appName) {
		return appRoleSvc.getUserRoleForApp(username,appName);
	}

	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	@ResponseBody
	public List<AppRole> getAppRolesForUser(@PathVariable String username) {
		return appRoleSvc.getAppRolesForUser(username);
	}
	
	@PreAuthorize("@securityHelper.isSarlaccAdmin()")
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public AppRole getCreateUserRoleForApp(@RequestBody AppRole appRole) {
		return appRoleSvc.createUserRoleForApp(appRole);
	}
	
	@PreAuthorize("@securityHelper.isSarlaccAdmin()")
	@RequestMapping(value="/{username}/{appName}", method=RequestMethod.DELETE)
	@ResponseStatus(code=HttpStatus.OK)
	public void deleteUserRoleForApp(@PathVariable String username, @PathVariable String appName) {
		appRoleSvc.deleteUserRoleForApp(username,appName);
	}
	
}
