package com.csci5308.groupme.auth.config;

import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.qos.logback.classic.Logger;

public class PasswordEncryptionConfigImpl implements PasswordEncryptionConfig{

	private BCryptPasswordEncoder encoder;
	private final Logger logger = (Logger) LoggerFactory.getLogger(PasswordEncryptionConfigImpl.class);
	
	public PasswordEncryptionConfigImpl()
	{
		encoder = new BCryptPasswordEncoder();
	}
	
	public String encryptPassword(String rawPassword)
	{
		return encoder.encode(rawPassword);
	}
	
	public boolean matches(String rawPassword, String encryptedPassword)
	{
		return encoder.matches(rawPassword, encryptedPassword);
	}

}
