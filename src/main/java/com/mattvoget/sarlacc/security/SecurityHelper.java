package com.mattvoget.sarlacc.security;

import com.mattvoget.sarlacc.models.User;
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

}
