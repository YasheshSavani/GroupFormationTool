package com.csci5308.groupme.user.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.auth.AuthConstants;
import com.csci5308.groupme.auth.config.PasswordProperties;
import com.csci5308.groupme.auth.service.EmailService;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import com.csci5308.groupme.user.service.UserServiceImpl;

import constants.Messages;
import errors.EditCodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    PasswordProperties passwordProperties;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/signup")
    public String showSignUpPage(Model model) {
        User user = new User();
        passwordProperties = SystemConfig.instance().getPasswordProperties();
        model.addAttribute("user", user);
        model.addAttribute(passwordProperties);
        return "auth/signup";
    }

    @PostMapping("/signup")
    public ModelAndView signUpUser(@ModelAttribute("user") User user) {
        ModelAndView mView = new ModelAndView("auth/signup");
        passwordProperties = SystemConfig.instance().getPasswordProperties();
        mView.addObject(passwordProperties);
        String message;
        int signupStatus = userService.register(user);
        if (signupStatus == EditCodes.EMAIL_EXISTS) {
            message = Messages.SIGNUP_SUCCESS;
        } else if (signupStatus == EditCodes.USERNAME_EXISTS) {
            message = Messages.USERNAME_EXISTS;
        } else {
            message = "Signed up successfuly!";
        }
        logger.info(user.getEmail());
        mView.addObject("message", message);
        return mView;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/login";
    }

    @GetMapping("/forgotPassword")
    public String forgotPasswordPage() {
        return "auth/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public ModelAndView userEmail(@RequestParam("email") String emailId) {
        User user = userService.getByEmail(emailId);
        ModelAndView mView;
        if (null == user) {
            mView = new ModelAndView("auth/emailnotfound");
        } else {
            boolean isSent = emailService.sendPasswordRecovery(emailId);
            if (isSent) {
                mView = new ModelAndView("auth/emailsent");
            } else {
                mView = new ModelAndView("auth/failurePage");
            }
        }
        return mView;
    }

    @GetMapping("/resetPassword")
    public ModelAndView resetPassword(
            @RequestParam(value = "secretCode", required = true, defaultValue = "none") String secretCode,
            @RequestParam(value = "email", required = true, defaultValue = "noEmail") String email) {
        ModelAndView mView;
        passwordProperties = SystemConfig.instance().getPasswordProperties();
        if (secretCode.equals(AuthConstants.SECRET_CODE)) {
            mView = new ModelAndView("auth/resetPassword");
            mView.addObject("email", email);
            mView.addObject(passwordProperties);
        } else {
            mView = new ModelAndView("auth/unauthorized");
        }
        return mView;
    }

    @PostMapping("/resetPassword")
    public ModelAndView changePassword(@RequestParam(value = "password", required = true) String newPassword,
                                       @RequestParam(value = "email", required = true, defaultValue = "noEmail") String email) {
        ModelAndView mView;
        int status = userService.updatePassword(email, newPassword);
        if (status == EditCodes.SUCCESS) {
            mView = new ModelAndView("auth/resetPasswordSuccess");
        } else if (status == EditCodes.EMAIL_DOES_NOT_EXIST) {
            mView = new ModelAndView("auth/noEmail");
        } else {
            mView = new ModelAndView("auth/failurePage");
        }
        return mView;
    }

}
