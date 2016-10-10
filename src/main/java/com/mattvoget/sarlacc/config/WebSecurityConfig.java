package com.mattvoget.sarlacc.config;

import com.mattvoget.sarlacc.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomAuthFailureHandler authFailHandler;

    @Autowired
    private CustomAuthSuccessHandler authSuccessHandler;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/home/**").permitAll()
                .antMatchers("/account/").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin().loginProcessingUrl("/authenticate")
                .usernameParameter("username").passwordParameter("password")
                .loginPage("/home")//.defaultSuccessUrl("/login-success")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailHandler)
                .and()
            .logout()
                .permitAll()
                .and()
            .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/public/**");
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

}
