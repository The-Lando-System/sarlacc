package com.mattvoget.sarlacc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mattvoget.sarlacc.models.AppRole;
import com.mattvoget.sarlacc.repositories.AppRoleRepository;
import com.mattvoget.sarlacc.security.SecurityHelper;

@Service
public class AppRoleService {

	
	@Autowired private AppRoleRepository appRoleRepo;
	@Autowired private SecurityHelper securityHelper;
	
	public AppRole getUserRoleForApp(String username, String appName) {
		if (!securityHelper.isTheSameUser(username)){
			throw new IllegalArgumentException("You do not have permission to check roles for " + username);
		}		
		return appRoleRepo.findByUsernameAndAppName(username, appName);
	}
	
	public List<AppRole> getAppRolesForUser(String username) {
		if (!securityHelper.isTheSameUser(username)){
			throw new IllegalArgumentException("You do not have permission to check roles for " + username);
		}
		return appRoleRepo.findByUsername(username);
	}
	
	@Transactional
	public AppRole createUserRoleForApp(AppRole appRole){
		return appRoleRepo.save(appRole);
	}
	
	@Transactional
	public void deleteUserRoleForApp(String username, String appName){
		AppRole appRole = appRoleRepo.findByUsernameAndAppName(username, appName);
		appRoleRepo.delete(appRole);
	}
	
}
