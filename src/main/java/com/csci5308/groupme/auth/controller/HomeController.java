package com.csci5308.groupme.auth.controller;

import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String applicationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
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
    public String toolUserHomePage(@RequestParam(value = "isStudent", required = false, defaultValue = "false") boolean isStudent,
                                   @RequestParam(value = "isTA", required = false, defaultValue = "false") boolean isTA,
                                   @RequestParam(value = "isInstructor", required = false, defaultValue = "false") boolean isInstructor, Principal principal) {

        if (isStudent && isTA && isInstructor) {
            return "redirect:/InstructorTAStudent";
        } else if (isInstructor && isStudent) {
            return "redirect:/InstructorStudent";
        } else if (isStudent && isTA) {
            return "redirect:/TACourses";
        } else if (isInstructor && isTA) {
            return "redirect:/InstructorTA";
        } else if (isInstructor) {
            return "redirect:/Instructor";
        } else if (isTA) {
            return "redirect:/TACourses";
        } else {
            return "redirect:/studenthomepage";
        }
    }

}


