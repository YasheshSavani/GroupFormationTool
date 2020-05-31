package com.csci5308.groupme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GroupmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupmeApplication.class, args);
	}

}
