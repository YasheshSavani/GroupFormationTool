package com.csci5308.groupme.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.auth.AuthConstants;
import com.csci5308.groupme.auth.config.PasswordProperties;
import com.csci5308.groupme.auth.service.EmailService;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import com.csci5308.groupme.user.service.UserServiceImpl;

import constants.Messages;
import errors.EditCodes;

@Controller
public class ForgotPasswordController {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	PasswordProperties passwordProperties;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;

	@GetMapping("/forgotPassword")
	public String forgotPasswordPage() {
		return "auth/forgotPassword";
	}

	@PostMapping("/forgotPassword")
	public ModelAndView userEmail(@RequestParam("email") String emailId) {
		User user = userService.getByEmail(emailId);
		ModelAndView modelAndView;
		if (null == user) {
			modelAndView = new ModelAndView("auth/emailnotfound");
		} else {
			boolean isSent = emailService.sendPasswordRecovery(emailId);
			if (isSent) {
				modelAndView = new ModelAndView("auth/emailsent");
			} else {
				modelAndView = new ModelAndView("auth/failurePage");
			}
		}
		return modelAndView;
	}

	@GetMapping("/resetPassword")
	public ModelAndView resetPassword(
			@RequestParam(value = "secretCode", required = true, defaultValue = "none") String secretCode,
			@RequestParam(value = "email", required = true, defaultValue = "noEmail") String email) {
		ModelAndView modelAndView;
		passwordProperties = SystemConfig.instance().getPasswordProperties();
		if (secretCode.equals(AuthConstants.SECRET_CODE)) {
			modelAndView = new ModelAndView("auth/resetPassword");
			modelAndView.addObject("email", email);
			modelAndView.addObject(passwordProperties);
		} else {
			modelAndView = new ModelAndView("auth/unauthorized");
		}
		return modelAndView;
	}

	@PostMapping("/resetPassword")
	public ModelAndView changePassword(@RequestParam(value = "password", required = true) String newPassword,
			@RequestParam(value = "email", required = true, defaultValue = "noEmail") String email) {
<<<<<<< HEAD
		ModelAndView mView = new ModelAndView();
		User user = userService.getByEmail(email);
		int passwordValidationStatus = userService.passwordPolicyCheck(user);
		if(passwordValidationStatus != EditCodes.FAILURE)
		{
			int status = userService.updatePassword(email, newPassword);
			if (status == EditCodes.SUCCESS) {
				mView = new ModelAndView("auth/resetPasswordSuccess");
			} else if (status == EditCodes.EMAIL_DOES_NOT_EXIST) {
				mView = new ModelAndView("auth/noEmail");
			} else {
				mView = new ModelAndView("auth/failurePage");
			}
		}
		else
        {
        	String message;
			message = Messages.PASSWORD_POLICY_MISMATCH;
			mView.addObject("message", message);
        }
		return mView;
=======
		ModelAndView modelAndView;
		int status = userService.updatePassword(email, newPassword);
		if (status == EditCodes.SUCCESS) {
			modelAndView = new ModelAndView("auth/resetPasswordSuccess");
		} else if (status == EditCodes.EMAIL_DOES_NOT_EXIST) {
			modelAndView = new ModelAndView("auth/noEmail");
		} else {
			modelAndView = new ModelAndView("auth/failurePage");
		}
		return modelAndView;
>>>>>>> develop
	}

}
