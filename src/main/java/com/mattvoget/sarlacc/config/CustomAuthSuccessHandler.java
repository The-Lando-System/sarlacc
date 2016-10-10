package com.mattvoget.sarlacc.config;

import com.mattvoget.sarlacc.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication auth) throws IOException, ServletException {
        User user = (User) auth.getPrincipal();
        res.setStatus(HttpStatus.OK.value());
        res.getWriter().write(String.format("{\"message\":\"Welcome %s!\"}",user.getUsername()));
    }

}
