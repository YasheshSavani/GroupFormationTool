package com.csci5308.groupme.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordSecurityConfig {
	
	private BCryptPasswordEncoder encoder;
	
	public PasswordSecurityConfig()
	{
		encoder = new BCryptPasswordEncoder();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    
    public boolean matches(String rawPassword, String encryptedPassword)
	{
		return encoder.matches(rawPassword, encryptedPassword);
	}

}
