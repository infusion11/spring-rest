package com.example.springrest;

import com.example.springrest.model.Role;
import com.example.springrest.model.User;
import com.example.springrest.service.RoleService;
import com.example.springrest.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestApplication.class, args);
	}


	@Bean
	CommandLineRunner run(RoleService roleService){
		return args -> {
			roleService.insertRole(new Role(null, "ROLE_USER"));
			roleService.insertRole(new Role(null, "ROLE_VIP"));
			roleService.insertRole(new Role(null, "ROLE_ADMIN"));
		};
	}


}
