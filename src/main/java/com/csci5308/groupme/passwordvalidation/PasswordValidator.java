package com.csci5308.groupme.passwordvalidation;

public abstract class PasswordValidator {
	
	public String constraint;
	public abstract boolean isValid(String password);
	public abstract String getValidatorName();
}
