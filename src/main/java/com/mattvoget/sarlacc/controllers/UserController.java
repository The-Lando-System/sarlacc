package com.mattvoget.sarlacc.controllers;

import com.mattvoget.sarlacc.models.User;
import com.mattvoget.sarlacc.utils.UserActivityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserActivityLogger userLogger;

    @PreAuthorize("@securityHelper.hasAccess()")
    @RequestMapping("/user-details")
    public @ResponseBody
    User user(Principal user) {
        OAuth2Authentication auth = (OAuth2Authentication) user;
        User currentUser = (User) auth.getUserAuthentication().getPrincipal();
        userLogger.logUserEvent(currentUser.getUsername(),UserController.class,"Returning User Details");
        return currentUser;
    }

    @RequestMapping("/me")
    public Principal user2(Principal principal) {
      return principal;
    }
}
