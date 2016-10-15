package com.mattvoget.sarlacc;

import com.mattvoget.sarlacc.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
@Import({WebSecurityConfig.class})
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
