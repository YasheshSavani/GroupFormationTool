package com.csci5308.groupme.passwordvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csci5308.groupme.auth.config.PasswordEncryptionConfigImpl;

public class MinimumLengthValidator extends PasswordValidator{
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(PasswordEncryptionConfigImpl.class);
	
	public MinimumLengthValidator(String constraint) {
		this.constraint = constraint;
	}
	
	@Override
	public boolean isValid(String password) {
		
		int minimumLength = Integer.parseInt(constraint);
		int passwordLength = password.length();
		if (passwordLength < minimumLength)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public String getValidatorName(){
		
		return PasswordValidatorName.MINIMUMLENGTH.toString();
	}

}
