package com.cdg.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cdg.business.model.AppRole;
import com.cdg.business.model.AppUser;
import com.cdg.business.service.AccountService;


@SpringBootApplication
public class BusinessApplication implements CommandLineRunner {
	
	@Autowired
	 AccountService accountService;
	 
	 @Bean
		public BCryptPasswordEncoder getBCPE() {
			return new BCryptPasswordEncoder();
		}

	public static void main(String[] args) {
		SpringApplication.run(BusinessApplication.class, args);
	}
	
	

	@Override
	public void run(String... args) throws Exception {
		/*
		accountService.saveUser(new AppUser(null, "admin","1234",null));
		accountService.saveUser(new AppUser(null, "user","1234",null));
		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"USER"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("user", "USER");
		*/
		
		
		
		
	}

}
