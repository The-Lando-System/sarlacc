package com.mattvoget.sarlacc.security;

import com.mattvoget.sarlacc.models.User;
import com.mattvoget.sarlacc.models.Role;
import com.mattvoget.sarlacc.utils.UserActivityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("securityHelper")
public class SecurityHelper {

    @Autowired
    private UserActivityLogger userLogger;

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

    private User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
