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

import com.mattvoget.sarlacc.models.App;
import com.mattvoget.sarlacc.services.AppService;

@Controller
@PreAuthorize("@securityHelper.isSarlaccAdmin()")
@RequestMapping(value="app")
public class AppController {

	@Autowired private AppService appSvc;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public List<App> getAllApps() {
		return appSvc.findAllApps();
	}
	
	@RequestMapping(value="/{appName}", method=RequestMethod.GET)
	@ResponseBody
	public App getAppByName(@PathVariable String appName) {
		return appSvc.findAppByName(appName);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public App createNewApp(@RequestBody App newApp) {
		return appSvc.createNewApp(newApp);
	}
	
	@RequestMapping(value="/{appName}", method=RequestMethod.DELETE)
	@ResponseStatus(code=HttpStatus.OK)
	public void deleteApp(@PathVariable String appName) {
		appSvc.deleteApp(appName);
	}
}
