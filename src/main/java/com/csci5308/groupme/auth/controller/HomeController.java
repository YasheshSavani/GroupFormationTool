package com.csci5308.groupme.auth.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;

@Controller
public class HomeController {

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String applicationPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "auth/index";
	}

	@GetMapping("/admin")
	public ModelAndView adminHomePage(Principal principal) {
		ModelAndView mView = new ModelAndView("admin/home_admin");
		mView.addObject("userName", principal.getName());
		return mView;
	}

	@GetMapping("/guest")
	public ModelAndView guestUserHomePage(Principal principal) {
		ModelAndView mView = new ModelAndView("guest/home_guest");
		mView.addObject("userName", principal.getName());
		return mView;
	}

	@GetMapping("/home")
	public ModelAndView toolUserHomePage(@RequestParam(value="isStudent", required=false, defaultValue="false") boolean isStudent,
			@RequestParam(value="isTA", required=false, defaultValue="false") boolean isTA,
			@RequestParam(value="isInstructor", required=false, defaultValue="false") boolean isInstructor, Principal principal) {
		logger.info("Student: ", isStudent);
		System.out.println(isStudent);
		ModelAndView mView = new ModelAndView("auth/home");
		mView.addObject("isStudent", isStudent);
		mView.addObject("isTA", isTA);
		mView.addObject("isInstructor", isInstructor);
		mView.addObject("userName", principal.getName());
		return mView;
	}
	
}


