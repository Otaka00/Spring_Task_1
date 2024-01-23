package com.example.FirstSpringProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FirstSpringProjectApplication {
	private static List<User> list = new ArrayList<User>();

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringProjectApplication.class, args);
		User u = new User("Ahmad","123",25);
		list.add(u);
		u = new User("Omar","15",25);
		u.setAge(30);
		u.setId("300");
		list.add(u);
		u = new User("Ali","125",25);
		list.add(u);
	}
	
}
