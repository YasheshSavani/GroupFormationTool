package com.csci5308.groupme.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import com.csci5308.groupme.user.service.UserServiceImpl;

import errors.EditCodes;

@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String applicationPage(Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
	    return "index";
	}
	
	@GetMapping("/home")
	public String homePage(Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
	    return "home";
	}
	
	@GetMapping("/signup")
	public String showRegistrationForm(Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
	    return "signup";
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public String signUpUser(@ModelAttribute("user") User user) {
		int signupStatus = userService.register(user);
		if(signupStatus == EditCodes.EMAIL_EXISTS)
			return "Email already exists!";
		else if(signupStatus == EditCodes.USERNAME_EXISTS)
			return "Username already exists! Try another one";
		logger.info(user.getEmail());
		return "Signed Up";
	}
	
}
