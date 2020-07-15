package com.csci5308.groupme.passwordvalidation;

import java.util.List;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.auth.config.PasswordSecurityConfig;
import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDao;
import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDaoImpl;
import com.csci5308.groupme.user.model.User;

public class PasswordHistoryValidator extends PasswordValidator{
	
	User user;
	
	public PasswordHistoryValidator(String constraint, User user) {
		
		this.constraint = constraint;
		this.user = user;
	}

	@Override
	public boolean isValid(String password) {
		
		PasswordValidationDao passwordValidationDao = new PasswordValidationDaoImpl();
		PasswordSecurityConfig passwordSecurityConfig = SystemConfig.instance().getPasswordSecurityConfig();
		
		List<String> previousPasswords = passwordValidationDao.getPreviousPasswordsByUsername(user.getUserName(), Integer.parseInt(constraint));
		for (int i = 0; i < previousPasswords.size(); i++){
			if(passwordSecurityConfig.matches(password, previousPasswords.get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getValidatorName() {
		return PasswordValidatorName.PASSWORDHISTORY.toString();
	}

}
