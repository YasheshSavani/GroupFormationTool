package com.csci5308.groupme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.csci5308.groupme.user.dao.UserDao;

@ComponentScan("com.csci5308")
@SpringBootApplication
public class GroupmeApplication {


	public static void main(String[] args) {
		SpringApplication.run(GroupmeApplication.class, args);
		
//			AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(GroupmeApplication.class);
//	    UserDao userDao = applicationContext.getBean(UserDao.class);
//		System.out.println(userDao.findByUserName("testuser1").getUserName());
//		
//		UserDetailsService userService = applicationContext.getBean(UserDetailsService.class);
//		System.out.println(userService.loadUserByUsername("admin_test").getUsername() + "hahaha");
//			PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);
//			String dbp = "$10$NQJKFGeMsB7x1wsL3v6TVu4e3a3S0wI9iepIKtTprFknU2QP244Va";
//			String formp = "admin19";
//			String encoded = passwordEncoder.encode(formp);
//			System.out.println(encoded);
//			if(passwordEncoder.matches(formp, dbp))
//					System.out.println("trueee");
	    
	    
	}

}
