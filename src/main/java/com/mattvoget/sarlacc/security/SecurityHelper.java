package com.mattvoget.sarlacc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mattvoget.sarlacc.models.AppRole;
import com.mattvoget.sarlacc.models.Role;
import com.mattvoget.sarlacc.models.User;
import com.mattvoget.sarlacc.services.AppRoleService;
import com.mattvoget.sarlacc.utils.UserActivityLogger;

@Component("securityHelper")
public class SecurityHelper {

    @Autowired private UserActivityLogger userLogger;
    @Autowired private AppRoleService appRoleService;
    
    public boolean hasAccess(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userLogger.logUserEvent(user.getUsername(),SecurityHelper.class,"Checking access");
        return true;
    }

    public boolean isAdmin(){
        User user = getUser();
        userLogger.logUserEvent(user.getUsername(),SecurityHelper.class,"Checking if user is admin");
        return (Role.ADMIN == getUser().getRole());
    }
    
    public boolean isSarlaccAdmin() {
    	User user = getUser();
    	AppRole appRole = appRoleService.getUserRoleForApp(user.getUsername(), "sarlacc");
    	return (Role.ADMIN == appRole.getRole());
    }

    public User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
